/* *****************************************************************************
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.incad.relief3.rdcz.uniimporter;

import cz.incad.relief3.rdcz.uniimporter.model.DataParserCsv;
import cz.incad.relief3.rdcz.uniimporter.model.OneIssue;
import cz.incad.relief3.rdcz.uniimporter.utils.OneSource;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *******************************************************************************
 *
 * @author martin.novacek@incad.cz
 */
public class Test_ParserCSV {

    private static final Logger LOG = Logger.getLogger(Test_ParserCSV.class.getName());

    /**
     ***************************************************************************
     *
     * @param args
     */
    public static void main(String[] args) {
        String arg0; //filename
        String arg1; //encoding
        String arg2; //separator
        OneIssue oneIssue = null;

        System.out.println(Test_ParserCSV.class.getName() + " starting...");

        if (args.length != 3) {
            System.out.println("ERROR: špatný počet parametrů");
            return;
        }

        arg0 = args[0];
        arg1 = args[1];
        arg2 = args[2];

        try {
            System.out.println("");
            //Press any key
            System.out.println(":: Properties ::");
            System.out.println("Filename : " + arg0);
            System.out.println("Encoding : " + arg1);
            System.out.println("Separator: " + arg2);
            System.out.println("");
            System.out.println("");

            DataParserCsv dp = new DataParserCsv();
            File file = new File(arg0);
            OneSource oneSource = new OneSource();
            oneIssue = new OneIssue(file, oneSource);
            oneIssue.source.source_parseMethod_argument1 = arg1; //CP1250
            oneIssue.source.source_parseMethod_argument2 = arg2; //"|"
            dp.parseIt(oneIssue);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            System.out.println("");
            System.out.println("");
            System.out.println(":: FAIL ::");
            if (oneIssue != null) {
                System.out.println(oneIssue.getLogFailInfo());
            }
        }
    }

}
