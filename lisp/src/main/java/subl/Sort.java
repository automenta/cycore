/* For LarKC */
package subl;

import subl.type.core.SubLObject;
import subl.type.core.SubLSequence;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLTrampolineFile;

public class Sort extends SubLTrampolineFile {
	public static SubLObject cmerge(SubLObject seq1, SubLObject seq2, SubLObject predicate, SubLObject key) {
		if (key == CommonSymbols.UNPROVIDED)
			key = CommonSymbols.IDENTITY;
		BinaryFunction predicateTyped = SubLTrampolineFile.extractBinaryFunc(predicate);
		UnaryFunction keyTyped = SubLTrampolineFile.extractUnaryFunc(key);
		return seq1.toSeq().merge(seq2.toSeq(), predicateTyped, keyTyped);
	}

	public static SubLObject sort(SubLObject sequence, SubLObject predicate) {
		return stable_sort(sequence, predicate);
	}

	public static SubLObject sort(SubLObject sequence, SubLObject predicate, SubLObject key) {
		return stable_sort(sequence, predicate, key);
	}

	public static SubLObject stable_sort(SubLObject sequence, SubLObject predicate) {
		SubLSequence sequenceTyped = sequence.toSeq();
		BinaryFunction predicateTyped = SubLTrampolineFile.extractBinaryFunc(predicate);
		SubLObject result = sequenceTyped.sort(true, predicateTyped);
		return result;
	}

	public static SubLObject stable_sort(SubLObject sequence, SubLObject predicate, SubLObject key) {
		if (key == CommonSymbols.UNPROVIDED)
			key = CommonSymbols.IDENTITY;
		if (key == CommonSymbols.IDENTITY)
			return stable_sort(sequence, predicate);
		SubLSequence sequenceTyped = sequence.toSeq();
		BinaryFunction predicateTyped = SubLTrampolineFile.extractBinaryFunc(predicate);
		UnaryFunction keyTyped = SubLTrampolineFile.extractUnaryFunc(key);
		SubLObject result = sequenceTyped.sort(true, predicateTyped, keyTyped);
		return result;
	}

	public static SubLFile me;
	static {
		me = new Sort();
	}

	@Override
	public void declareFunctions() {
		SubLFiles.declareFunction(Sort.me, "sort", "SORT", 2, 1, false);
		SubLFiles.declareFunction(Sort.me, "stable_sort", "STABLE-SORT", 2, 1, false);
		SubLFiles.declareFunction(Sort.me, "cmerge", "CMERGE", 3, 1, false);
	}

	@Override
	public void initializeVariables() {
	}

	@Override
	public void runTopLevelForms() {
	}
}
