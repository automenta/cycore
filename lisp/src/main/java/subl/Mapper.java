/* For LarKC */
package subl;

import subl.jrtl.nativeCode.mapper.LoadMap;
import subl.jrtl.nativeCode.mapper.Snapshot;
import subl.jrtl.translatedCode.sublisp.compatibility;
import subl.jrtl.translatedCode.sublisp.streams_high;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;
import subl.type.stream.SubLStream;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLFiles;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Mapper implements SubLFile {
	private static void list_image_index_internal() {
		List<SubLSymbol> candidates = SubLPackage.CYC_PACKAGE.worldInitializedSymbols();
		for (SubLSymbol symbol : candidates) {
			String symbolName = symbol.getName();
			System.out.println(";;; CYC::" + symbolName);
		}
	}

	private static String mapFileFromStateFileName(String stateFileName) {
		return stateFileName.concat(".map");
	}

	private static void restore_image_internal(SubLObject filename) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private static SubLObject write_image_internal(SubLObject subLFileName) throws IOException {
		LoadMap loadMap = writeStateFile(subLFileName);
		writeMapFile(subLFileName, loadMap);
		return CommonSymbols.T;
	}

	private static void writeMapFile(SubLObject subLFileName, LoadMap loadMap) throws IOException {
		String mapFileName = mapFileFromStateFileName(subLFileName.getStringValue());
		OutputStream os = new BufferedOutputStream(new FileOutputStream(mapFileName));
		loadMap.save(os);
		os.close();
	}

	private static LoadMap writeStateFile(SubLObject subLFileName) throws IOException {
		LoadMap loadMap = LoadMap.newLoadMap();
		SubLObject rawStream = SubLNil.NIL;
		SubLObject prev_streambuf_size = Dynamic.currentBinding(StreamsLow.$stream_initial_output_buffer_size$);
		try {
			Dynamic.rebind(StreamsLow.$stream_initial_output_buffer_size$, SubLObjectFactory.makeInteger(1048576));
			rawStream = compatibility.open_binary(subLFileName, CommonSymbols.OUTPUT_KEYWORD);
		} finally {
			Dynamic.rebind(StreamsLow.$stream_initial_output_buffer_size$, prev_streambuf_size);
		}
		SubLStream stateStream = rawStream.toOutputStream();
		writeStateInformation(stateStream, loadMap);
		streams_high.close(rawStream, SubLNil.NIL);
		return loadMap;
	}

	private static void writeStateInformation(SubLStream stateStream, LoadMap loadMap) throws IOException {
		Snapshot snapShot = new Snapshot(stateStream);
		Snapshot.SnapShootingListener mapListener = loadMap.getListener();
		snapShot.addListener(mapListener);
		snapShot.addListener(Mapper.TRACE_LISTENER);
		snapShot.addFilter(Mapper.NOT_IMPLEMENTED_FILTER);
		snapShot.snapShoot();
		snapShot.removeFilter(Mapper.NOT_IMPLEMENTED_FILTER);
		snapShot.removeListener(mapListener);
		snapShot.removeListener(Mapper.TRACE_LISTENER);
	}

	public static SubLObject list_image_index() {
		list_image_index_internal();
		return SubLNil.NIL;
	}

	public static SubLObject restore_image(SubLObject filename, SubLObject do_full_gc) {
		if (do_full_gc == CommonSymbols.UNPROVIDED)
			do_full_gc = SubLNil.NIL;
		restore_image_internal(filename);
		if (do_full_gc == CommonSymbols.T)
			System.gc();
		return SubLNil.NIL;
	}

	public static SubLObject write_image(SubLObject filename, SubLObject do_full_gc) {
		if (do_full_gc == CommonSymbols.UNPROVIDED)
			do_full_gc = SubLNil.NIL;
		if (do_full_gc == CommonSymbols.T)
			System.gc();
		try {
			return write_image_internal(filename);
		} catch (IOException xcpt) {
			Errors.error(xcpt);
			return SubLNil.NIL;
		}
	}

	public static SubLFile me;
	private static Snapshot.SnapShootingListener TRACE_LISTENER;
	private static Snapshot.SnapShootingFilter NOT_IMPLEMENTED_FILTER;
	static {
		me = new Mapper();
		TRACE_LISTENER = new Snapshot.SnapShootingListener() {
			@Override
			public void noteSymbol(String pkgName, String symbolName, SubLObject filePosition) {
				System.out.println(";;; writing " + pkgName + "::" + symbolName + " at " + filePosition);
			}

			@Override
			public void skippedSymbol(String pkgName, String symbolName, SubLSymbol type, Exception xcpt) {
				System.out.println(";;; skipping " + pkgName + "::" + symbolName + " with value of type "
						+ type.getName() + ": " + xcpt.getMessage());
			}

			@Override
			public void skippedSymbol(String pkgName, String symbolName, SubLSymbol type, StackOverflowError err) {
				System.out.println(";;; skipping " + pkgName + "::" + symbolName + " with value of type "
						+ type.getName() + ": " + err.getMessage());
			}

			@Override
			public void skippedSymbol(String pkgName, String symbolName, SubLSymbol type, String errmsg) {
				System.out.println(";;; skipping " + pkgName + "::" + symbolName + " with value of type "
						+ type.getName() + " due to SubL error: " + errmsg);
			}
		};
		NOT_IMPLEMENTED_FILTER = value -> value.isLock() || value.isProcess() || value.isStream();
	}

	@Override
	public void declareFunctions() {
		SubLFiles.declareFunction(Mapper.me, "write_image", "WRITE-IMAGE", 1, 1, false);
		SubLFiles.declareFunction(Mapper.me, "restore_image", "RESTORE-IMAGE", 1, 1, false);
		SubLFiles.declareFunction(Mapper.me, "list_image_index", "%LIST-IMAGE-INDEX", 0, 0, false);
	}

	@Override
	public void initializeVariables() {
	}

	@Override
	public void runTopLevelForms() {
	}
}
