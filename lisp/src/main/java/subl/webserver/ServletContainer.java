/* For LarKC */
package subl.webserver;

import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLTrampolineFile;

import java.util.ArrayList;
import java.util.List;

public class ServletContainer extends SubLTrampolineFile {
    public static class WarSpec {
        public WarSpec(String file, String contextRoot) {
            filename = file;
            this.contextRoot = contextRoot;
        }

        private String filename;
        private String contextRoot;

        public String getContextRoot() {
            return contextRoot;
        }

        public String getFilename() {
            return filename;
        }
    }

    public static SubLObject startServletContainerInt(int portNum, List<WarSpec> warSpecs, SubLObject tmpDirString) {
        String tmpDir = tmpDirString == UNPROVIDED ? null : tmpDirString.getStringValue();
        Jetty.startJettyServer(portNum, warSpecs, tmpDir);
        return NIL;
    }

    public static SubLObject startServletContainerInt(SubLObject port, SubLObject fileSpecs, SubLObject tmpDirString) {
        String tmpDir = tmpDirString == UNPROVIDED ? null : tmpDirString.getStringValue();
        int portNum = port.toInteger().intValue();
        List<WarSpec> warSpecs = new ArrayList<WarSpec>();
        for (SubLObject fileSpec : fileSpecs.asConsList().toSubLObjectArray()) {
            SubLList alistEntry = fileSpec.asConsList();
            warSpecs.add(new WarSpec(alistEntry.first().getStringValue(), alistEntry.rest().getStringValue()));
        }
        Jetty.startJettyServer(portNum, warSpecs, tmpDir);
        return NIL;
    }

    public static SubLObject stopServletContainerInt(SubLObject tmpDirString) {
        String tmpDir = tmpDirString == UNPROVIDED ? null : tmpDirString.getStringValue();
        Jetty.stopJettyServer(tmpDir);
        return NIL;
    }

    public static SubLFile me;
    static {
        me = new ServletContainer();
    }

    @Override
    public void declareFunctions() {
        SubLFiles.declareFunction(ServletContainer.me, "startServletContainerInt", "START-SERVLET-CONTAINER-INT", 2, 1, false);
        SubLFiles.declareFunction(ServletContainer.me, "stopServletContainerInt", "STOP-SERVLET-CONTAINER-INT", 0, 1, false);
    }

    @Override
    public void initializeVariables() {
    }

    @Override
    public void runTopLevelForms() {
    }
}
