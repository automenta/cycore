/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.wordnet_import;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.union;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.cycl_grammar;
import com.cyc.cycjava.cycl.genls;
import com.cyc.cycjava.cycl.lexicon_accessors;
import com.cyc.cycjava.cycl.lexicon_vars;
import com.cyc.cycjava.cycl.subl_promotions;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_infrastructure_utilities;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      WORDNET-UTILITIES
 * source file: /cyc/top/cycl/wordnet-import/wordnet-utilities.lisp
 * created:     2019/07/03 17:38:13
 */
public final class wordnet_utilities extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new wordnet_utilities();

 public static final String myName = "com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities";


    @LispMethod(comment = "Internal Constants")
    // Internal Constants
    private static final SubLObject $const0$WordNet2_0_KS = reader_make_constant_shell("WordNet2_0-KS");

    private static final SubLSymbol $WN_SKS_REGISTERED = makeKeyword("WN-SKS-REGISTERED");

    static private final SubLString $str2$__WordNet2_0_KS_is_not_registered = makeString("#$WordNet2_0-KS is not registered");

    static private final SubLList $list3 = list(reader_make_constant_shell("Noun"), reader_make_constant_shell("Verb"), reader_make_constant_shell("Adjective"), reader_make_constant_shell("Adverb"));

    private static final SubLSymbol CYCL_REPRESENTED_TERM_P = makeSymbol("CYCL-REPRESENTED-TERM-P");

    private static final SubLSymbol WNI_EXACT_SYNSET_TERM_MATCHES = makeSymbol("WNI-EXACT-SYNSET-TERM-MATCHES");

    private static final SubLSymbol WNI_STRONG_SYNSET_TERM_MATCHES = makeSymbol("WNI-STRONG-SYNSET-TERM-MATCHES");

    private static final SubLSymbol WN_WEAK_SYNSET_TERM_MATCHES_WITH_POS_CHECK = makeSymbol("WN-WEAK-SYNSET-TERM-MATCHES-WITH-POS-CHECK");

    static private final SubLString $str12$Wrong_MATCH_TYPE_input___S__ = makeString("Wrong MATCH-TYPE input: ~S~%");

    // Definitions
    public static final SubLObject wn_check_sks_registered_alt() {
        if (NIL != sksi_infrastructure_utilities.sk_source_registeredP($const0$WordNet2_0_KS, UNPROVIDED)) {
            return $WN_SKS_REGISTERED;
        }
        return Errors.error($str_alt2$__WordNet2_0_KS_is_not_registered);
    }

    // Definitions
    public static SubLObject wn_check_sks_registered() {
        if (NIL != sksi_infrastructure_utilities.sk_source_registeredP($const0$WordNet2_0_KS, UNPROVIDED)) {
            return $WN_SKS_REGISTERED;
        }
        return Errors.error($str2$__WordNet2_0_KS_is_not_registered);
    }

    public static final SubLObject wn_string_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        return lexicon_accessors.get_strings_of_type(word_unit, pos, UNPROVIDED, UNPROVIDED, UNPROVIDED).first();
    }

    public static SubLObject wn_string_for_word_unitXpos(final SubLObject word_unit, final SubLObject pos) {
        return lexicon_accessors.get_strings_of_type(word_unit, pos, UNPROVIDED, UNPROVIDED, UNPROVIDED).first();
    }

    /**
     * Resolves the input part-of-speech POS to a part-of-speech allowed by WordNet
     *
     * @param POS
     * 		cycl-represented-term-p; an instance of #$SpeechPart
     * @return cycl-represented-term-p; one of (#$Noun #$Verb #$Adjective #$Adverb)
     */
    @LispMethod(comment = "Resolves the input part-of-speech POS to a part-of-speech allowed by WordNet\r\n\r\n@param POS\r\n\t\tcycl-represented-term-p; an instance of #$SpeechPart\r\n@return cycl-represented-term-p; one of (#$Noun #$Verb #$Adjective #$Adverb)")
    public static final SubLObject wn_allowed_pos_alt(SubLObject pos) {
        {
            SubLObject allowed_pos = $list_alt3;
            if (NIL != subl_promotions.memberP(pos, allowed_pos, EQ, UNPROVIDED)) {
                return pos;
            } else {
                return intersection(genls.all_genls(pos, UNPROVIDED, UNPROVIDED), allowed_pos, EQ, UNPROVIDED).first();
            }
        }
    }

    /**
     * Resolves the input part-of-speech POS to a part-of-speech allowed by WordNet
     *
     * @param POS
     * 		cycl-represented-term-p; an instance of #$SpeechPart
     * @return cycl-represented-term-p; one of (#$Noun #$Verb #$Adjective #$Adverb)
     */
    @LispMethod(comment = "Resolves the input part-of-speech POS to a part-of-speech allowed by WordNet\r\n\r\n@param POS\r\n\t\tcycl-represented-term-p; an instance of #$SpeechPart\r\n@return cycl-represented-term-p; one of (#$Noun #$Verb #$Adjective #$Adverb)")
    public static SubLObject wn_allowed_pos(final SubLObject pos) {
        final SubLObject allowed_pos = $list3;
        if (NIL != subl_promotions.memberP(pos, allowed_pos, EQ, UNPROVIDED)) {
            return pos;
        }
        return intersection(genls.all_genls(pos, UNPROVIDED, UNPROVIDED), allowed_pos, EQ, UNPROVIDED).first();
    }

    /**
     * Returns an ordered list of lists where each member list contains synonyms for
     * POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns an ordered list of lists where each member list contains synonyms for\r\nPOS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains synonyms for\nPOS form of WORD-UNIT.")
    public static final SubLObject wn_ranked_synonyms_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject synonyms = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    synonyms = cons(wordnet_import.wni_synset_words(synset), synonyms);
                }
            }
            return nreverse(synonyms);
        }
    }

    /**
     * Returns an ordered list of lists where each member list contains synonyms for
     * POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns an ordered list of lists where each member list contains synonyms for\r\nPOS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains synonyms for\nPOS form of WORD-UNIT.")
    public static SubLObject wn_ranked_synonyms_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject synonyms = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            synonyms = cons(wordnet_import.wni_synset_words(synset), synonyms);
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return nreverse(synonyms);
    }

    /**
     * Returns a list of synonyms for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns a list of synonyms for POS form of WORD-UNIT.")
    public static final SubLObject wn_all_synonyms_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject synonyms = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    synonyms = union(synonyms, wordnet_import.wni_synset_words(synset), EQUAL, UNPROVIDED);
                }
            }
            return synonyms;
        }
    }

    /**
     * Returns a list of synonyms for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns a list of synonyms for POS form of WORD-UNIT.")
    public static SubLObject wn_all_synonyms_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject synonyms = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            synonyms = union(synonyms, wordnet_import.wni_synset_words(synset), EQUAL, UNPROVIDED);
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return synonyms;
    }

    /**
     * Returns an ordered list of glosses for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns an ordered list of glosses for POS form of WORD-UNIT.")
    public static final SubLObject wn_glosses_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject glosses = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    {
                        SubLObject gloss = wordnet_import.wni_synset_gloss(synset);
                        if (NIL != gloss) {
                            glosses = cons(gloss, glosses);
                        }
                    }
                }
            }
            return nreverse(glosses);
        }
    }

    @LispMethod(comment = "Returns an ordered list of glosses for POS form of WORD-UNIT.")
    public static SubLObject wn_glosses_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject glosses = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject gloss = wordnet_import.wni_synset_gloss(synset);
            if (NIL != gloss) {
                glosses = cons(gloss, glosses);
            }
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return nreverse(glosses);
    }

    /**
     * Returns an ordered list of lists where each member list contains example
     * phrases for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns an ordered list of lists where each member list contains example\r\nphrases for POS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains example\nphrases for POS form of WORD-UNIT.")
    public static final SubLObject wn_ranked_example_phrases_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject example_phrases = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    {
                        SubLObject example_phrase = wordnet_import.wni_synset_example_phrases(synset);
                        if (NIL != example_phrase) {
                            example_phrases = cons(example_phrase, example_phrases);
                        }
                    }
                }
            }
            return nreverse(example_phrases);
        }
    }

    @LispMethod(comment = "Returns an ordered list of lists where each member list contains example\r\nphrases for POS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains example\nphrases for POS form of WORD-UNIT.")
    public static SubLObject wn_ranked_example_phrases_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject example_phrases = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject example_phrase = wordnet_import.wni_synset_example_phrases(synset);
            if (NIL != example_phrase) {
                example_phrases = cons(example_phrase, example_phrases);
            }
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return nreverse(example_phrases);
    }

    /**
     * Returns a list of example phrases for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns a list of example phrases for POS form of WORD-UNIT.")
    public static final SubLObject wn_all_example_phrases_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject example_phrases = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    example_phrases = union(example_phrases, wordnet_import.wni_synset_example_phrases(synset), UNPROVIDED, UNPROVIDED);
                }
            }
            return example_phrases;
        }
    }

    @LispMethod(comment = "Returns a list of example phrases for POS form of WORD-UNIT.")
    public static SubLObject wn_all_example_phrases_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject example_phrases = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            example_phrases = union(example_phrases, wordnet_import.wni_synset_example_phrases(synset), UNPROVIDED, UNPROVIDED);
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return example_phrases;
    }

    /**
     * Returns an ordered list of lists where each member list contains hypernyms
     * for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns an ordered list of lists where each member list contains hypernyms\r\nfor POS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains hypernyms\nfor POS form of WORD-UNIT.")
    public static final SubLObject wn_ranked_hypernyms_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject hypernyms = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    {
                        SubLObject current_hypernyms = NIL;
                        SubLObject cdolist_list_var_1 = wordnet_import.wni_hypernyms(synset);
                        SubLObject hypernym_synset = NIL;
                        for (hypernym_synset = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , hypernym_synset = cdolist_list_var_1.first()) {
                            current_hypernyms = union(wordnet_import.wni_synset_words(hypernym_synset), current_hypernyms, EQUAL, UNPROVIDED);
                        }
                        if (NIL != current_hypernyms) {
                            hypernyms = cons(current_hypernyms, hypernyms);
                        }
                    }
                }
            }
            return nreverse(hypernyms);
        }
    }

    @LispMethod(comment = "Returns an ordered list of lists where each member list contains hypernyms\r\nfor POS form of WORD-UNIT.\nReturns an ordered list of lists where each member list contains hypernyms\nfor POS form of WORD-UNIT.")
    public static SubLObject wn_ranked_hypernyms_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject hypernyms = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current_hypernyms = NIL;
            SubLObject cdolist_list_var_$1 = wordnet_import.wni_hypernyms(synset);
            SubLObject hypernym_synset = NIL;
            hypernym_synset = cdolist_list_var_$1.first();
            while (NIL != cdolist_list_var_$1) {
                current_hypernyms = union(wordnet_import.wni_synset_words(hypernym_synset), current_hypernyms, EQUAL, UNPROVIDED);
                cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                hypernym_synset = cdolist_list_var_$1.first();
            } 
            if (NIL != current_hypernyms) {
                hypernyms = cons(current_hypernyms, hypernyms);
            }
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return nreverse(hypernyms);
    }

    /**
     * Returns a list of hypernyms for POS form of WORD-UNIT.
     */
    @LispMethod(comment = "Returns a list of hypernyms for POS form of WORD-UNIT.")
    public static final SubLObject wn_all_hypernyms_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos) {
        SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
        SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
        com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
        {
            SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
            SubLObject hypernyms = NIL;
            pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
            {
                SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                SubLObject synset = NIL;
                for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_2 = wordnet_import.wni_hypernyms(synset);
                        SubLObject hypernym_synset = NIL;
                        for (hypernym_synset = cdolist_list_var_2.first(); NIL != cdolist_list_var_2; cdolist_list_var_2 = cdolist_list_var_2.rest() , hypernym_synset = cdolist_list_var_2.first()) {
                            hypernyms = union(wordnet_import.wni_synset_words(hypernym_synset), hypernyms, EQUAL, UNPROVIDED);
                        }
                    }
                }
            }
            return hypernyms;
        }
    }

    @LispMethod(comment = "Returns a list of hypernyms for POS form of WORD-UNIT.")
    public static SubLObject wn_all_hypernyms_for_word_unitXpos(final SubLObject word_unit, SubLObject pos) {
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject hypernyms = NIL;
        pos = wn_allowed_pos(pos);
        SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
        SubLObject synset = NIL;
        synset = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$2 = wordnet_import.wni_hypernyms(synset);
            SubLObject hypernym_synset = NIL;
            hypernym_synset = cdolist_list_var_$2.first();
            while (NIL != cdolist_list_var_$2) {
                hypernyms = union(wordnet_import.wni_synset_words(hypernym_synset), hypernyms, EQUAL, UNPROVIDED);
                cdolist_list_var_$2 = cdolist_list_var_$2.rest();
                hypernym_synset = cdolist_list_var_$2.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            synset = cdolist_list_var.first();
        } 
        return hypernyms;
    }

    public static final SubLObject wn_weak_synset_term_matches_with_pos_check_alt(SubLObject synset, SubLObject collectionP) {
        return wordnet_import.wni_weak_synset_term_matches(synset, collectionP, T);
    }

    public static SubLObject wn_weak_synset_term_matches_with_pos_check(final SubLObject synset, final SubLObject collectionP) {
        return wordnet_import.wni_weak_synset_term_matches(synset, collectionP, T);
    }

    /**
     * Returns an ordered list of lists where each member list contains potential
     * CycL terms that represent POS form of WORD-UNIT.
     *
     * @param MATCH-TYPE
     * 		keywordp; :exact, :strong, or :weak
     * @param TERM-TYPE
     * 		keywordp; :all, :collections, or :individuals
     */
    @LispMethod(comment = "Returns an ordered list of lists where each member list contains potential\r\nCycL terms that represent POS form of WORD-UNIT.\r\n\r\n@param MATCH-TYPE\r\n\t\tkeywordp; :exact, :strong, or :weak\r\n@param TERM-TYPE\r\n\t\tkeywordp; :all, :collections, or :individuals\nReturns an ordered list of lists where each member list contains potential\nCycL terms that represent POS form of WORD-UNIT.")
    public static final SubLObject wn_ranked_candidate_cycls_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos, SubLObject match_type, SubLObject term_type, SubLObject lexicon_mt) {
        if (match_type == UNPROVIDED) {
            match_type = $WEAK;
        }
        if (term_type == UNPROVIDED) {
            term_type = $ALL;
        }
        if (lexicon_mt == UNPROVIDED) {
            lexicon_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
            SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
            com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
            {
                SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
                SubLObject match_function = NIL;
                SubLObject cycls = NIL;
                {
                    SubLObject pcase_var = match_type;
                    if (pcase_var.eql($EXACT)) {
                        match_function = WNI_EXACT_SYNSET_TERM_MATCHES;
                    } else {
                        if (pcase_var.eql($STRONG)) {
                            match_function = WNI_STRONG_SYNSET_TERM_MATCHES;
                        } else {
                            if (pcase_var.eql($WEAK)) {
                                match_function = WN_WEAK_SYNSET_TERM_MATCHES_WITH_POS_CHECK;
                            } else {
                                Errors.error($str_alt12$Wrong_MATCH_TYPE_input___S__, match_type);
                            }
                        }
                    }
                }
                {
                    SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
                    try {
                        lexicon_vars.$lexicon_lookup_mt$.bind(lexicon_mt, thread);
                        pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
                        {
                            SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                            SubLObject synset = NIL;
                            for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                                if (match_type == $EXACT) {
                                    cycls = cons(funcall(match_function, synset), cycls);
                                } else {
                                    {
                                        SubLObject pcase_var = term_type;
                                        if (pcase_var.eql($ALL)) {
                                            cycls = cons(union(funcall(match_function, synset, T), funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED), cycls);
                                        } else {
                                            if (pcase_var.eql($COLLECTIONS)) {
                                                cycls = cons(funcall(match_function, synset, T), cycls);
                                            } else {
                                                if (pcase_var.eql($INDIVIDUALS)) {
                                                    cycls = cons(funcall(match_function, synset, NIL), cycls);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return remove(NIL, nreverse(cycls), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            }
        }
    }

    @LispMethod(comment = "Returns an ordered list of lists where each member list contains potential\r\nCycL terms that represent POS form of WORD-UNIT.\r\n\r\n@param MATCH-TYPE\r\n\t\tkeywordp; :exact, :strong, or :weak\r\n@param TERM-TYPE\r\n\t\tkeywordp; :all, :collections, or :individuals\nReturns an ordered list of lists where each member list contains potential\nCycL terms that represent POS form of WORD-UNIT.")
    public static SubLObject wn_ranked_candidate_cycls_for_word_unitXpos(final SubLObject word_unit, SubLObject pos, SubLObject match_type, SubLObject term_type, SubLObject lexicon_mt) {
        if (match_type == UNPROVIDED) {
            match_type = $WEAK;
        }
        if (term_type == UNPROVIDED) {
            term_type = $ALL;
        }
        if (lexicon_mt == UNPROVIDED) {
            lexicon_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject match_function = NIL;
        SubLObject cycls = NIL;
        final SubLObject pcase_var = match_type;
        if (pcase_var.eql($EXACT)) {
            match_function = WNI_EXACT_SYNSET_TERM_MATCHES;
        } else
            if (pcase_var.eql($STRONG)) {
                match_function = WNI_STRONG_SYNSET_TERM_MATCHES;
            } else
                if (pcase_var.eql($WEAK)) {
                    match_function = WN_WEAK_SYNSET_TERM_MATCHES_WITH_POS_CHECK;
                } else {
                    Errors.error($str12$Wrong_MATCH_TYPE_input___S__, match_type);
                }


        final SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
        try {
            lexicon_vars.$lexicon_lookup_mt$.bind(lexicon_mt, thread);
            pos = wn_allowed_pos(pos);
            SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
            SubLObject synset = NIL;
            synset = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (match_type == $EXACT) {
                    cycls = cons(funcall(match_function, synset), cycls);
                } else {
                    final SubLObject pcase_var2 = term_type;
                    if (pcase_var2.eql($ALL)) {
                        cycls = cons(union(funcall(match_function, synset, T), funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED), cycls);
                    } else
                        if (pcase_var2.eql($COLLECTIONS)) {
                            cycls = cons(funcall(match_function, synset, T), cycls);
                        } else
                            if (pcase_var2.eql($INDIVIDUALS)) {
                                cycls = cons(funcall(match_function, synset, NIL), cycls);
                            }


                }
                cdolist_list_var = cdolist_list_var.rest();
                synset = cdolist_list_var.first();
            } 
        } finally {
            lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
        }
        return remove(NIL, nreverse(cycls), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     * Returns a list of potential CycL terms that represent POS form of WORD-UNIT.
     *
     * @param MATCH-TYPE
     * 		keywordp; :all, :exact, :strong, :weak, or :string
     * @param TERM-TYPE
     * 		keywordp; :all, :collections, or :individuals
     */
    @LispMethod(comment = "Returns a list of potential CycL terms that represent POS form of WORD-UNIT.\r\n\r\n@param MATCH-TYPE\r\n\t\tkeywordp; :all, :exact, :strong, :weak, or :string\r\n@param TERM-TYPE\r\n\t\tkeywordp; :all, :collections, or :individuals")
    public static final SubLObject wn_all_candidate_cycls_for_word_unitXpos_alt(SubLObject word_unit, SubLObject pos, SubLObject match_type, SubLObject term_type, SubLObject lexicon_mt) {
        if (match_type == UNPROVIDED) {
            match_type = $WEAK;
        }
        if (term_type == UNPROVIDED) {
            term_type = $ALL;
        }
        if (lexicon_mt == UNPROVIDED) {
            lexicon_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(word_unit, CYCL_REPRESENTED_TERM_P);
            SubLTrampolineFile.checkType(pos, CYCL_REPRESENTED_TERM_P);
            com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_check_sks_registered();
            {
                SubLObject word_string = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_string_for_word_unitXpos(word_unit, pos);
                SubLObject match_function = NIL;
                SubLObject cycls = NIL;
                {
                    SubLObject pcase_var = match_type;
                    if (pcase_var.eql($EXACT)) {
                        match_function = WNI_EXACT_SYNSET_TERM_MATCHES;
                    } else {
                        if (pcase_var.eql($STRONG)) {
                            match_function = WNI_STRONG_SYNSET_TERM_MATCHES;
                        } else {
                            if (pcase_var.eql($WEAK)) {
                                match_function = WN_WEAK_SYNSET_TERM_MATCHES_WITH_POS_CHECK;
                            } else {
                                Errors.error($str_alt12$Wrong_MATCH_TYPE_input___S__, match_type);
                            }
                        }
                    }
                }
                {
                    SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
                    try {
                        lexicon_vars.$lexicon_lookup_mt$.bind(lexicon_mt, thread);
                        pos = com.cyc.cycjava.cycl.wordnet_import.wordnet_utilities.wn_allowed_pos(pos);
                        {
                            SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
                            SubLObject synset = NIL;
                            for (synset = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , synset = cdolist_list_var.first()) {
                                if (match_type == $EXACT) {
                                    cycls = union(cycls, funcall(match_function, synset), UNPROVIDED, UNPROVIDED);
                                } else {
                                    {
                                        SubLObject pcase_var = term_type;
                                        if (pcase_var.eql($ALL)) {
                                            cycls = union(cycls, funcall(match_function, synset, T), UNPROVIDED, UNPROVIDED);
                                            cycls = union(cycls, funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED);
                                        } else {
                                            if (pcase_var.eql($COLLECTIONS)) {
                                                cycls = union(cycls, funcall(match_function, synset, T), UNPROVIDED, UNPROVIDED);
                                            } else {
                                                if (pcase_var.eql($INDIVIDUALS)) {
                                                    cycls = union(cycls, funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return cycls;
            }
        }
    }

    /**
     * Returns a list of potential CycL terms that represent POS form of WORD-UNIT.
     *
     * @param MATCH-TYPE
     * 		keywordp; :all, :exact, :strong, :weak, or :string
     * @param TERM-TYPE
     * 		keywordp; :all, :collections, or :individuals
     */
    @LispMethod(comment = "Returns a list of potential CycL terms that represent POS form of WORD-UNIT.\r\n\r\n@param MATCH-TYPE\r\n\t\tkeywordp; :all, :exact, :strong, :weak, or :string\r\n@param TERM-TYPE\r\n\t\tkeywordp; :all, :collections, or :individuals")
    public static SubLObject wn_all_candidate_cycls_for_word_unitXpos(final SubLObject word_unit, SubLObject pos, SubLObject match_type, SubLObject term_type, SubLObject lexicon_mt) {
        if (match_type == UNPROVIDED) {
            match_type = $WEAK;
        }
        if (term_type == UNPROVIDED) {
            term_type = $ALL;
        }
        if (lexicon_mt == UNPROVIDED) {
            lexicon_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != cycl_grammar.cycl_represented_term_p(word_unit) : "! cycl_grammar.cycl_represented_term_p(word_unit) " + ("cycl_grammar.cycl_represented_term_p(word_unit) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(word_unit) ") + word_unit;
        assert NIL != cycl_grammar.cycl_represented_term_p(pos) : "! cycl_grammar.cycl_represented_term_p(pos) " + ("cycl_grammar.cycl_represented_term_p(pos) " + "CommonSymbols.NIL != cycl_grammar.cycl_represented_term_p(pos) ") + pos;
        wn_check_sks_registered();
        final SubLObject word_string = wn_string_for_word_unitXpos(word_unit, pos);
        SubLObject match_function = NIL;
        SubLObject cycls = NIL;
        final SubLObject pcase_var = match_type;
        if (pcase_var.eql($EXACT)) {
            match_function = WNI_EXACT_SYNSET_TERM_MATCHES;
        } else
            if (pcase_var.eql($STRONG)) {
                match_function = WNI_STRONG_SYNSET_TERM_MATCHES;
            } else
                if (pcase_var.eql($WEAK)) {
                    match_function = WN_WEAK_SYNSET_TERM_MATCHES_WITH_POS_CHECK;
                } else {
                    Errors.error($str12$Wrong_MATCH_TYPE_input___S__, match_type);
                }


        final SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
        try {
            lexicon_vars.$lexicon_lookup_mt$.bind(lexicon_mt, thread);
            pos = wn_allowed_pos(pos);
            SubLObject cdolist_list_var = wordnet_import.wni_synsets_for_english_word_stringXpos(word_string, pos);
            SubLObject synset = NIL;
            synset = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (match_type == $EXACT) {
                    cycls = union(cycls, funcall(match_function, synset), UNPROVIDED, UNPROVIDED);
                } else {
                    final SubLObject pcase_var2 = term_type;
                    if (pcase_var2.eql($ALL)) {
                        cycls = union(cycls, funcall(match_function, synset, T), UNPROVIDED, UNPROVIDED);
                        cycls = union(cycls, funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED);
                    } else
                        if (pcase_var2.eql($COLLECTIONS)) {
                            cycls = union(cycls, funcall(match_function, synset, T), UNPROVIDED, UNPROVIDED);
                        } else
                            if (pcase_var2.eql($INDIVIDUALS)) {
                                cycls = union(cycls, funcall(match_function, synset, NIL), UNPROVIDED, UNPROVIDED);
                            }


                }
                cdolist_list_var = cdolist_list_var.rest();
                synset = cdolist_list_var.first();
            } 
        } finally {
            lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
        }
        return cycls;
    }

    public static SubLObject declare_wordnet_utilities_file() {
        declareFunction("wn_check_sks_registered", "WN-CHECK-SKS-REGISTERED", 0, 0, false);
        declareFunction("wn_string_for_word_unitXpos", "WN-STRING-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_allowed_pos", "WN-ALLOWED-POS", 1, 0, false);
        declareFunction("wn_ranked_synonyms_for_word_unitXpos", "WN-RANKED-SYNONYMS-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_all_synonyms_for_word_unitXpos", "WN-ALL-SYNONYMS-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_glosses_for_word_unitXpos", "WN-GLOSSES-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_ranked_example_phrases_for_word_unitXpos", "WN-RANKED-EXAMPLE-PHRASES-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_all_example_phrases_for_word_unitXpos", "WN-ALL-EXAMPLE-PHRASES-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_ranked_hypernyms_for_word_unitXpos", "WN-RANKED-HYPERNYMS-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_all_hypernyms_for_word_unitXpos", "WN-ALL-HYPERNYMS-FOR-WORD-UNIT&POS", 2, 0, false);
        declareFunction("wn_weak_synset_term_matches_with_pos_check", "WN-WEAK-SYNSET-TERM-MATCHES-WITH-POS-CHECK", 2, 0, false);
        declareFunction("wn_ranked_candidate_cycls_for_word_unitXpos", "WN-RANKED-CANDIDATE-CYCLS-FOR-WORD-UNIT&POS", 2, 3, false);
        declareFunction("wn_all_candidate_cycls_for_word_unitXpos", "WN-ALL-CANDIDATE-CYCLS-FOR-WORD-UNIT&POS", 2, 3, false);
        return NIL;
    }

    public static SubLObject init_wordnet_utilities_file() {
        return NIL;
    }

    public static SubLObject setup_wordnet_utilities_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_wordnet_utilities_file();
    }

    @Override
    public void initializeVariables() {
        init_wordnet_utilities_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_wordnet_utilities_file();
    }

    static {
    }

    static private final SubLString $str_alt2$__WordNet2_0_KS_is_not_registered = makeString("#$WordNet2_0-KS is not registered");

    static private final SubLList $list_alt3 = list(reader_make_constant_shell("Noun"), reader_make_constant_shell("Verb"), reader_make_constant_shell("Adjective"), reader_make_constant_shell("Adverb"));

    static private final SubLString $str_alt12$Wrong_MATCH_TYPE_input___S__ = makeString("Wrong MATCH-TYPE input: ~S~%");
}

/**
 * Total time: 87 ms
 */
