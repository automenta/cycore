package com.cyc.cycjava.cycl.inference.modules.removal;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.control_vars.$inference_debugP$;
import static com.cyc.cycjava.cycl.control_vars.$typical_hl_module_check_cost$;
import static com.cyc.cycjava.cycl.el_utilities.el_binary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_extensional_set_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_set_size;
import static com.cyc.cycjava.cycl.el_utilities.extensional_set_elements;
import static com.cyc.cycjava.cycl.el_utilities.make_el_extensional_set;
import static com.cyc.cycjava.cycl.el_utilities.make_el_list;
import static com.cyc.cycjava.cycl.el_utilities.make_el_set;
import static com.cyc.cycjava.cycl.el_utilities.make_negation;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.divide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.sqrt;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeDouble;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.print;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.V10;
import com.cyc.cycjava.cycl.arguments;
import com.cyc.cycjava.cycl.arity;
import com.cyc.cycjava.cycl.backward;
import com.cyc.cycjava.cycl.bindings;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.czer_utilities;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.fort_types_interface;
import com.cyc.cycjava.cycl.kb_accessors;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.quantities;
import com.cyc.cycjava.cycl.relation_evaluation;
import com.cyc.cycjava.cycl.unification_utilities;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.inference.harness.inference_modules;
import com.cyc.cycjava.cycl.inference.modules.preference_modules;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.JavaLink;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFloat;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class removal_modules_math extends SubLTranslatedFile implements V10 {
    public static final SubLFile me = new removal_modules_math();

    public static final String myName = "com.cyc.cycjava_2.cycl.inference.modules.removal.removal_modules_math";


    // defparameter
    public static final SubLSymbol $default_math_module_cost$ = makeSymbol("*DEFAULT-MATH-MODULE-COST*");

    private static final SubLObject $$interpolationWithCubicSpline = reader_make_constant_shell(makeString("interpolationWithCubicSpline"));



    private static final SubLSymbol $REMOVAL_INTERPOLATION_WITH_CUBIC_SPLINE = makeKeyword("REMOVAL-INTERPOLATION-WITH-CUBIC-SPLINE");

    private static final SubLList $list3 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("interpolationWithCubicSpline")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("interpolationWithCubicSpline")), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("NOT-FULLY-BOUND"), makeKeyword("FULLY-BOUND")), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")))), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-INTERPOLATION-WITH-CUBIC-SPLINE-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$interpolationWithCubicSpline <set-of-points> <2d-point-with-x-or-y-bound>)"), makeKeyword("EXAMPLE"), makeString("(#$interpolationWithCubicSpline \n    (#$TheSet \n     (#$The2DPoint \n      (#$Meter 440.1) \n      (#$DegreeCelsius 160.3)) \n     (#$The2DPoint \n      (#$Meter 441.1) \n      (#$DegreeCelsius 161.4)) \n     (#$The2DPoint \n      (#$Meter 442.3) \n      (#$DegreeCelsius 163.6)) \n     (#$The2DPoint \n      (#$Meter 443.6) \n      (#$DegreeCelsius 165.5)) \n     (#$The2DPoint \n      (#$Meter 444.9) \n      (#$DegreeCelsius 166.4)) \n     (#$The2DPoint \n      (#$Meter 445.8) \n      (#$DegreeCelsius 166.3)) \n     (#$The2DPoint \n      (#$Meter 446.7) \n      (#$DegreeCelsius 165.4)) \n     (#$The2DPoint \n      (#$Meter 447.9) \n      (#$DegreeCelsius 167.8))) \n    (#$The2DPoint \n     (#$Meter 445.0) \n     (#$DegreeCelsius ?VALUE)))") });

    private static final SubLSymbol JAVA_LINK_X_Y_CUBIC_SPLINE_FIND_Y_FOR_X = makeSymbol("JAVA-LINK-X-Y-CUBIC-SPLINE-FIND-Y-FOR-X");

    private static final SubLObject $const5$interpolationWithPiecewiseLinearP = reader_make_constant_shell(makeString("interpolationWithPiecewiseLinearPolynomial"));

    private static final SubLSymbol $REMOVAL_INTERPOLATION_WITH_PIECEWISE_LINEAR_POLYNOMIAL = makeKeyword("REMOVAL-INTERPOLATION-WITH-PIECEWISE-LINEAR-POLYNOMIAL");

    private static final SubLList $list7 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("interpolationWithPiecewiseLinearPolynomial")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("interpolationWithPiecewiseLinearPolynomial")), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("NOT-FULLY-BOUND"), makeKeyword("FULLY-BOUND")), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")))), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-INTERPOLATION-WITH-PIECEWISE-LINEAR-POLYNOMIAL-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$interpolationWithPiecewiseLinearPolynomial <set-of-points> <2d-point-with-x-or-y-bound>)"), makeKeyword("EXAMPLE"), makeString("(#$interpolationWithPiecewiseLinearPolynomial \n    (#$TheSet \n     (#$The2DPoint \n      (#$Meter 440.1) \n      (#$DegreeCelsius 160.3)) \n     (#$The2DPoint \n      (#$Meter 441.1) \n      (#$DegreeCelsius 161.4)) \n     (#$The2DPoint \n      (#$Meter 442.3) \n      (#$DegreeCelsius 163.6)) \n     (#$The2DPoint \n      (#$Meter 443.6) \n      (#$DegreeCelsius 165.5)) \n     (#$The2DPoint \n      (#$Meter 444.9) \n      (#$DegreeCelsius 166.4)) \n     (#$The2DPoint \n      (#$Meter 445.8) \n      (#$DegreeCelsius 166.3)) \n     (#$The2DPoint \n      (#$Meter 446.7) \n      (#$DegreeCelsius 165.4)) \n     (#$The2DPoint \n      (#$Meter 447.9) \n      (#$DegreeCelsius 167.8))) \n    (#$The2DPoint \n     (#$Meter 445.0) \n     (#$DegreeCelsius ?VALUE)))") });

    private static final SubLSymbol JAVA_LINK_X_Y_PIECEWISE_LINEAR_FIND_Y_FOR_X = makeSymbol("JAVA-LINK-X-Y-PIECEWISE-LINEAR-FIND-Y-FOR-X");

    private static final SubLObject $const9$extrapolationWithSimpleLinearRegr = reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegression"));

    private static final SubLSymbol $REMOVAL_EXTRAPOLATION_WITH_SIMPLE_LINEAR_REGRESSION = makeKeyword("REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION");

    private static final SubLList $list11 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegression")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegression")), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("NOT-FULLY-BOUND"), makeKeyword("FULLY-BOUND")), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")))), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$extrapolationWithSimpleLinearRegression <set-of-points> <2d-point-with-x-or-y-bound>)"), makeKeyword("EXAMPLE"), makeString("(#$extrapolationWithSimpleLinearRegression \n    (#$TheSet \n     (#$The2DPoint \n      (#$DaysDuration 1) \n      (#$DegreeCelsius 160.3)) \n     (#$The2DPoint \n      (#$DaysDuration 2) \n      (#$DegreeCelsius 161.4)) \n     (#$The2DPoint \n      (#$DaysDuration 3) \n      (#$DegreeCelsius 163.6)) \n     (#$The2DPoint \n      (#$DaysDuration 4) \n      (#$DegreeCelsius 165.5)) \n     (#$The2DPoint \n      (#$DaysDuration 5) \n      (#$DegreeCelsius 166.4)) \n     (#$The2DPoint \n      (#$DaysDuration 6) \n      (#$DegreeCelsius 166.3)) \n     (#$The2DPoint \n      (#$DaysDuration 7) \n      (#$DegreeCelsius 165.4)) \n     (#$The2DPoint \n      (#$DaysDuration 8) \n      (#$DegreeCelsius 167.8))) \n    (#$The2DPoint \n     (#$DaysDuration ?VALUE) \n     (#$DegreeCelsius 200)))") });

    private static final SubLSymbol JAVA_LINK_X_Y_REGRESSION_FIND_Y_FOR_X = makeSymbol("JAVA-LINK-X-Y-REGRESSION-FIND-Y-FOR-X");

    private static final SubLSymbol EL_2D_POINT_P = makeSymbol("EL-2D-POINT-P");

    private static final SubLList $list14 = list(makeSymbol("THE-2D-POINT"), makeSymbol("QUERY-X-VALUE-WITH-UNITS"), makeSymbol("QUERY-Y-VALUE-WITH-UNITS"));





    private static final SubLObject $const17$extrapolationWithSimpleLinearRegr = reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegressionWithErrorBars"));

    private static final SubLSymbol $kw18$REMOVAL_EXTRAPOLATION_WITH_SIMPLE_LINEAR_REGRESSION_WITH_ERROR_BA = makeKeyword("REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION-WITH-ERROR-BARS");

    private static final SubLList $list19 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegressionWithErrorBars")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("extrapolationWithSimpleLinearRegressionWithErrorBars")), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("NOT-FULLY-BOUND"), makeKeyword("FULLY-BOUND")), list(reader_make_constant_shell(makeString("The2DPoint")), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND"))), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION-WITH-ERROR-BARS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$extrapolationWithSimpleLinearRegressionWithErrorBars <set-of-points> <2d-point-with-x-or-y-bound> <error-term-unbound>)"), makeKeyword("EXAMPLE"), makeString("(#$extrapolationWithSimpleLinearRegressionWithErrorBars\n    (#$TheSet \n     (#$The2DPoint \n      (#$DaysDuration 1) \n      (#$DegreeCelsius 160.3)) \n     (#$The2DPoint \n      (#$DaysDuration 2) \n      (#$DegreeCelsius 161.4)) \n     (#$The2DPoint \n      (#$DaysDuration 3) \n      (#$DegreeCelsius 163.6)) \n     (#$The2DPoint \n      (#$DaysDuration 4) \n      (#$DegreeCelsius 165.5)) \n     (#$The2DPoint \n      (#$DaysDuration 5) \n      (#$DegreeCelsius 166.4)) \n     (#$The2DPoint \n      (#$DaysDuration 6) \n      (#$DegreeCelsius 166.3)) \n     (#$The2DPoint \n      (#$DaysDuration 7) \n      (#$DegreeCelsius 165.4)) \n     (#$The2DPoint \n      (#$DaysDuration 8) \n      (#$DegreeCelsius 167.8))) \n    (#$The2DPoint \n     (#$DaysDuration ?VALUE) \n     (#$DegreeCelsius 200))\n    ?ERROR)") });

    private static final SubLSymbol JAVA_LINK_X_Y_REGRESSION_FIND_Y_FOR_X_WITH_ERROR_BARS = makeSymbol("JAVA-LINK-X-Y-REGRESSION-FIND-Y-FOR-X-WITH-ERROR-BARS");

    private static final SubLObject $const21$regressionLinesForSetsIntersectAt = reader_make_constant_shell(makeString("regressionLinesForSetsIntersectAtPoint"));

    private static final SubLSymbol $REMOVAL_REGRESSION_LINES_FOR_SETS_INTERSECT_AT_POINT = makeKeyword("REMOVAL-REGRESSION-LINES-FOR-SETS-INTERSECT-AT-POINT");

    private static final SubLList $list23 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("regressionLinesForSetsIntersectAtPoint")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("regressionLinesForSetsIntersectAtPoint")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-REGRESSION-LINES-FOR-SETS-INTERSECT-AT-POINT-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$regressionLinesForSetsIntersectAtPoint <set-of-points> <set-of-points> ?POINT)"), makeKeyword("EXAMPLE"), makeString("(#$regressionLinesForSetsIntersectAtPoint\n     (#$TheSet\n      (#$The2DPoint\n       (#$DegreeCelsius 200)\n       (#$SecondsDuration 1263118210))\n      (#$The2DPoint\n       (#$DegreeCelsius 210)\n       (#$SecondsDuration 1263204610))\n      (#$The2DPoint\n       (#$DegreeCelsius 220)\n       (#$SecondsDuration 1263291010)))\n     (TheSet\n      (#$The2DPoint\n       (#$DegreeCelsius 211)\n       (#$SecondsDuration 1263081600))\n      (#$The2DPoint\n       (#$DegreeCelsius 212)\n       (#$SecondsDuration 1263168000))\n      (#$The2DPoint\n       (#$DegreeCelsius 213)\n       (#$SecondsDuration 1263254400)))\n     ?POINT)") });

    private static final SubLObject $$The2DPoint = reader_make_constant_shell(makeString("The2DPoint"));

    private static final SubLObject $const25$slopeOfRegressionLineForSetOfPoin = reader_make_constant_shell(makeString("slopeOfRegressionLineForSetOfPoints"));

    private static final SubLSymbol $REMOVAL_SLOPE_OF_REGRESSION_LINE_FOR_SET_OF_POINTS = makeKeyword("REMOVAL-SLOPE-OF-REGRESSION-LINE-FOR-SET-OF-POINTS");

    private static final SubLList $list27 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("slopeOfRegressionLineForSetOfPoints")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("slopeOfRegressionLineForSetOfPoints")), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SLOPE-OF-REGRESSION-LINE-FOR-SET-OF-POINTS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$slopeOfRegressionLineForSetOfPoints <set-of-points> ?SLOPE)"), makeKeyword("EXAMPLE"), makeString("(#$slopeOfRegressionLineForSetOfPoints\n     (#$TheSet\n      (#$The2DPoint\n       (#$DegreeCelsius 211)\n       (#$SecondsDuration 1263081600))\n      (#$The2DPoint\n       (#$DegreeCelsius 212)\n       (#$SecondsDuration 1263168000))\n      (#$The2DPoint\n       (#$DegreeCelsius 213)\n       (#$SecondsDuration 1263254400))) ?X)") });

    private static final SubLObject $const28$setOfFirstDerivativePointsBasedOn = reader_make_constant_shell(makeString("setOfFirstDerivativePointsBasedOnSetOfPoints"));

    private static final SubLSymbol $REMOVAL_SET_OF_FIRST_DERIVATIVE_POINTS_BASED_ON_SET_OF_POINTS = makeKeyword("REMOVAL-SET-OF-FIRST-DERIVATIVE-POINTS-BASED-ON-SET-OF-POINTS");

    private static final SubLList $list30 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("setOfFirstDerivativePointsBasedOnSetOfPoints")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("setOfFirstDerivativePointsBasedOnSetOfPoints")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SET-OF-FIRST-DERIVATIVE-POINTS-BASED-ON-SET-OF-POINTS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$setOfFirstDerivativePointsBasedOnSetOfPoints <set-of-points> ?SET-OF-POINTS)"), makeKeyword("EXAMPLE"), makeString("(#$setOfFirstDerivativePointsBasedOnSetOfPoints\n    (#$TheSet\n     (#$The2DPoint (#$SecondsDuration 1) (#$DegreeCelsius 1))\n     (#$The2DPoint (#$SecondsDuration 3) (#$DegreeCelsius 5))\n     (#$The2DPoint (#$SecondsDuration 5) (#$DegreeCelsius 1)))\n    #$CubicSpline\n    ?X)\n    -->\n    (#$TheSet \n     (#$The2DPoint (#$SecondsDuration 1) ((#$PerFn #$DegreeCelsius #$SecondsDuration) 6.0))\n     (#$The2DPoint (#$SecondsDuration 3) ((#$PerFn #$DegreeCelsius #$SecondsDuration) 0.0))\n     (#$The2DPoint (#$SecondsDuration 5) ((#$PerFn #$DegreeCelsius #$SecondsDuration) -6.0)))") });

    private static final SubLObject $const31$setHasPointsWithYValueRelnToMatch = reader_make_constant_shell(makeString("setHasPointsWithYValueRelnToMatchingXValuePointsInSet"));

    private static final SubLSymbol $kw32$REMOVAL_SET_HAS_POINTS_WITH_Y_VALUE_RELN_TO_MATCHING_X_VALUE_POIN = makeKeyword("REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO-MATCHING-X-VALUE-POINTS-IN-SET");

    private static final SubLList $list33 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("setHasPointsWithYValueRelnToMatchingXValuePointsInSet")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("setHasPointsWithYValueRelnToMatchingXValuePointsInSet")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO-MATCHING-X-VALUE-POINTS-IN-SET-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$setHasPointsWithYValueRelnToMatchingXValuePointsInSet <set-of-points> <set-of-points> <binary-function> <binary-predicate> <value-in-y-units> ?RESULT-SET-OF-POINTS)"), makeKeyword("EXAMPLE"), makeString("(#$setHasPointsWithYValueRelnToMatchingXValuePointsInSet \n    (#$TheSet \n      (#$The2DPoint (#$Meter 702) (#$Kelvin 177.5)) \n      (#$The2DPoint (#$Meter 703) (#$Kelvin 177.5)) \n      (#$The2DPoint (#$Meter 704) (#$Kelvin 177.5)) \n      (#$The2DPoint (#$Meter 705) (#$Kelvin 170))) \n    (#$TheSet \n      (#$The2DPoint (#$Meter 702) (#$Kelvin 175)) \n      (#$The2DPoint (#$Meter 703) (#$Kelvin 175)) \n      (#$The2DPoint (#$Meter 704) (#$Kelvin 175)) \n      (#$The2DPoint (#$Meter 705) (#$Kelvin 170)))\n    #$DifferenceFn\n    #$greaterThanOrEqualTo \n    (#$Kelvin 2)\n    ?NEWSET)\n    -->\n    (((?NEWSET #$TheSet (#$The2DPoint (#$Meter 702) (#$Kelvin 177.5)) \n                        (#$The2DPoint (#$Meter 703) (#$Kelvin 177.5)) \n                        (#$The2DPoint (#$Meter 704) (#$Kelvin 177.5)))))") });



    private static final SubLString $str35$org_apache_commons_math_stat_regr = makeString("org.apache.commons.math.stat.regression.SimpleRegression");

    private static final SubLString $$$addData = makeString("addData");

    private static final SubLString $$$double = makeString("double");

    private static final SubLString $$$getSlope = makeString("getSlope");

    private static final SubLString $$$getIntercept = makeString("getIntercept");





    private static final SubLString $str42$org_apache_commons_math_analysis_ = makeString("org.apache.commons.math.analysis.interpolation.SplineInterpolator");

    private static final SubLString $$$interpolate = makeString("interpolate");

    private static final SubLString $str44$_D = makeString("[D");

    private static final SubLString $str45$org_apache_commons_math_analysis_ = makeString("org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction");

    private static final SubLString $$$value = makeString("value");

    private static final SubLSymbol $sym47$_ = makeSymbol("<");



    private static final SubLString $str49$org_apache_commons_math_analysis_ = makeString("org.apache.commons.math.analysis.interpolation.LinearInterpolator");

    private static final SubLString $$$predict = makeString("predict");

    private static final SubLString $$$getSumSquaredErrors = makeString("getSumSquaredErrors");

    private static final SubLString $str52$org_apache_commons_math_geometry_ = makeString("org.apache.commons.math.geometry.euclidean.twod.Line");

    private static final SubLString $str53$org_apache_commons_math_geometry_ = makeString("org.apache.commons.math.geometry.euclidean.twod.Vector2D");

    private static final SubLString $$$intersection = makeString("intersection");

    private static final SubLString $str55$org_apache_commons_math_geometry_ = makeString("org.apache.commons.math.geometry.partitioning.Hyperplane");

    private static final SubLString $$$getX = makeString("getX");

    private static final SubLString $$$getY = makeString("getY");

    private static final SubLObject $$CubicSpline = reader_make_constant_shell(makeString("CubicSpline"));

    private static final SubLObject $const59$LinearPolynomialFunction_Piecewis = reader_make_constant_shell(makeString("LinearPolynomialFunction-Piecewise"));

    private static final SubLString $$$derivative = makeString("derivative");



    private static final SubLObject $$setHasPointsWithYValueRelnTo = reader_make_constant_shell(makeString("setHasPointsWithYValueRelnTo"));

    private static final SubLSymbol $REMOVAL_SET_HAS_POINTS_WITH_Y_VALUE_RELN_TO = makeKeyword("REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO");

    private static final SubLList $list64 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("setHasPointsWithYValueRelnTo")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("setHasPointsWithYValueRelnTo")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$setHasPointsWithYValueRelnTo <set-of-points> <operator> <comparator> <quant1> <quant2> ?NEWSET)"), makeKeyword("EXAMPLE"), makeString("(#$setHasPointsWithYValueRelnTo \n (#$TheSet \n  (#$The2DPoint \n   (#$Meter 20.0) \n   (#$DegreeCelsius 68.35)) \n  (#$The2DPoint \n   (#$Meter 21.0) \n   (#$DegreeCelsius 72.98)) \n  (#$The2DPoint \n   (#$Meter 22.0) \n   (#$DegreeCelsius 75.68)) \n  (#$The2DPoint \n   (#$Meter 23.0) \n   (#$DegreeCelsius 78.16)) \n  (#$The2DPoint \n   (#$Meter 24.0) \n   (#$DegreeCelsius 80.79)))\n #$PlusFn\n #$lessThanOrEqualTo \n (#$DegreeCelsius 0) \n (#$DegreeCelsius 70) ?NEWSET)\n -->\n (((?NEWSET TheSet\n   (The2DPoint\n       (Meter 20.0)\n       (DegreeCelsius 68.35)))))") });

    private static final SubLObject $$operationResultComparison = reader_make_constant_shell(makeString("operationResultComparison"));



    private static final SubLSymbol $REMOVAL_OPERATION_RESULT_COMPARISON_POS_CHECK = makeKeyword("REMOVAL-OPERATION-RESULT-COMPARISON-POS-CHECK");

    private static final SubLList $list68 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("operationResultComparison")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("operationResultComparison")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-OPERATION-RESULT-COMPARISON-POS-CHECK"), makeKeyword("DOCUMENTATION"), makeString("(#$operationResultComparison <OP> <A> <B> <COMP> <QUANT>)"), makeKeyword("EXAMPLE"), makeString("(#$operationResultComparison #$PlusFn (#$DegreeCelsius 50) (#$DegreeCelsius 0) #$lessThanOrEqualTo (#$DegreeCelsius 70))\n   --> True") });

    private static final SubLSymbol $REMOVAL_OPERATION_RESULT_COMPARISON_NEG_CHECK = makeKeyword("REMOVAL-OPERATION-RESULT-COMPARISON-NEG-CHECK");

    private static final SubLList $list70 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("NEG"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("operationResultComparison")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("operationResultComparison")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-OPERATION-RESULT-COMPARISON-NEG-CHECK"), makeKeyword("DOCUMENTATION"), makeString("(#$operationResultComparison <OP> <A> <B> <COMP> <QUANT>)"), makeKeyword("EXAMPLE"), makeString("(#$not (#$operationResultComparison #$PlusFn (#$DegreeCelsius 100) (#$DegreeCelsius 0) #$lessThanOrEqualTo (#$DegreeCelsius 70))\n   --> True") });

    private static final SubLObject $$inflowLengthsWithStartingDepths = reader_make_constant_shell(makeString("inflowLengthsWithStartingDepths"));

    private static final SubLSymbol $REMOVAL_INFLOW_LENGTHS_WITH_STARTING_DEPTHS = makeKeyword("REMOVAL-INFLOW-LENGTHS-WITH-STARTING-DEPTHS");

    private static final SubLList $list73 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell(makeString("inflowLengthsWithStartingDepths")), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell(makeString("inflowLengthsWithStartingDepths")), makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND"), makeKeyword("NOT-FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-MATH-MODULE-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-INFLOW-LENGTHS-WITH-STARTING-DEPTHS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$inflowLengthsWithStartingDepths <well> <set-of-points> ?LENGTH-AND-DEPTHS)"), makeKeyword("EXAMPLE"), makeString("(#$inflowLengthsWithStartingDepths\n  (#$The #$Well)\n  (#$TheSet \n   (#$The2DPoint (#$Meter 100) (#$DegreeCelsius  95))\n   (#$The2DPoint (#$Meter 110) (#$DegreeCelsius 110))\n   (#$The2DPoint (#$Meter 120) (#$DegreeCelsius 115))\n   (#$The2DPoint (#$Meter 130) (#$DegreeCelsius 114))\n   (#$The2DPoint (#$Meter 140) (#$DegreeCelsius 103))\n   (#$The2DPoint (#$Meter 150) (#$DegreeCelsius  95))\n   (#$The2DPoint (#$Meter 160) (#$DegreeCelsius 100))\n   (#$The2DPoint (#$Meter 170) (#$DegreeCelsius 102))\n   (#$The2DPoint (#$Meter 180) (#$DegreeCelsius 102))\n   (#$The2DPoint (#$Meter 190) (#$DegreeCelsius  91))\n   (#$The2DPoint (#$Meter 200) (#$DegreeCelsius 100))\n   (#$The2DPoint (#$Meter 210) (#$DegreeCelsius 110))\n   (#$The2DPoint (#$Meter 220) (#$DegreeCelsius 105))\n   (#$The2DPoint (#$Meter 230) (#$DegreeCelsius  97))\n   (#$The2DPoint (#$Meter 240) (#$DegreeCelsius  96))\n   (#$The2DPoint (#$Meter 250) (#$DegreeCelsius  95))\n   (#$The2DPoint (#$Meter 260) (#$DegreeCelsius  94))\n   (#$The2DPoint (#$Meter 270) (#$DegreeCelsius  93))\n   (#$The2DPoint (#$Meter 280) (#$DegreeCelsius  92)))\n  ?LENGTH-AND-DEPTHS)") });

    private static final SubLObject $$Meter = reader_make_constant_shell(makeString("Meter"));

    private static final SubLObject $$DegreeCelsius = reader_make_constant_shell(makeString("DegreeCelsius"));

    private static final SubLString $str76$____ = makeString("~%~%");

    private static final SubLList $list77 = list(makeSymbol("X-VALUE"), makeSymbol("Y-VALUE"));

    private static final SubLString $str78$High_value__x__A_y__A__ = makeString("High value: x=~A y=~A~%");

    private static final SubLList $list79 = list(makeSymbol("CUR-X-VALUE"), makeSymbol("CUR-Y-DERIVATIVE-VALUE"));

    private static final SubLString $str80$checking_x__A_deriv__A__ = makeString("checking x=~A deriv=~A~%");

    private static final SubLString $$$Bad_looping = makeString("Bad looping");

    private static final SubLFloat $float$0_5 = makeDouble(0.5);

    private static final SubLFloat $float$_0_5 = makeDouble(-0.5);

    private static final SubLString $str84$Got_interval___A__A___ = makeString("Got interval (~A ~A)~%");

    private static final SubLSymbol $sym85$_ = makeSymbol(">");

    private static final SubLString $str86$inflow_lengths___A__ = makeString("inflow-lengths: ~A~%");

    private static final SubLList $list87 = list(makeSymbol("START"), makeSymbol("END"));

    private static final SubLString $str88$merged_inflow_lengths___A__ = makeString("merged-inflow-lengths: ~A~%");

    private static final SubLObject $$TheList = reader_make_constant_shell(makeString("TheList"));

    public static SubLObject removal_interpolation_with_cubic_spline_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return removal_interpolation_or_extrapolation_with_java_link_function_expand_helper(asent, JAVA_LINK_X_Y_CUBIC_SPLINE_FIND_Y_FOR_X);
    }

    public static SubLObject removal_interpolation_with_piecewise_linear_polynomial_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return removal_interpolation_or_extrapolation_with_java_link_function_expand_helper(asent, JAVA_LINK_X_Y_PIECEWISE_LINEAR_FIND_Y_FOR_X);
    }

    public static SubLObject removal_extrapolation_with_simple_linear_regression_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return removal_interpolation_or_extrapolation_with_java_link_function_expand_helper(asent, JAVA_LINK_X_Y_REGRESSION_FIND_Y_FOR_X);
    }

    public static SubLObject removal_interpolation_or_extrapolation_with_java_link_function_expand_helper(final SubLObject asent, final SubLObject java_link_function) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject query_point = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        if (NIL != el_extensional_set_p(set_of_points_in_units)) {
            final SubLObject v_2d_points = extensional_set_elements(set_of_points_in_units);
            if ((NIL != v_2d_points) && (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points, UNPROVIDED))) {
                thread.resetMultipleValues();
                final SubLObject x_y_values = explode_2d_points_with_units(v_2d_points, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                if (((NIL != x_y_values) && (NIL != x_units)) && (NIL != y_units)) {
                    SubLObject current;
                    final SubLObject datum = current = query_point;
                    SubLObject the_2d_point = NIL;
                    SubLObject query_x_value_with_units = NIL;
                    SubLObject query_y_value_with_units = NIL;
                    destructuring_bind_must_consp(current, datum, $list14);
                    the_2d_point = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list14);
                    query_x_value_with_units = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list14);
                    query_y_value_with_units = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL != variables.not_fully_bound_p(query_y_value_with_units)) {
                            thread.resetMultipleValues();
                            final SubLObject query_x_units = quantities.explode_interval(query_x_value_with_units);
                            final SubLObject query_x_value = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            final SubLObject normalized_query_x_value = quantities.convert_to_units_absolute(x_units, query_x_units, query_x_value, UNPROVIDED);
                            final SubLObject answer_y_value = funcall(java_link_function, x_y_values, normalized_query_x_value);
                            if (NIL != answer_y_value) {
                                final SubLObject answer_y_value_with_units = quantities.make_interval(y_units, answer_y_value, UNPROVIDED);
                                final SubLObject v_bindings = quantities.term_unify_with_units(query_y_value_with_units, answer_y_value_with_units);
                                backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                            }
                        } else {
                            final SubLObject y_x_values = Mapping.mapcar(NREVERSE, x_y_values);
                            thread.resetMultipleValues();
                            final SubLObject query_y_units = quantities.explode_interval(query_y_value_with_units);
                            final SubLObject query_y_value = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            final SubLObject normalized_query_y_value = quantities.convert_to_units_absolute(y_units, query_y_units, query_y_value, UNPROVIDED);
                            final SubLObject answer_x_value = funcall(java_link_function, y_x_values, normalized_query_y_value);
                            if (NIL != answer_x_value) {
                                final SubLObject answer_x_value_with_units = quantities.make_interval(x_units, answer_x_value, UNPROVIDED);
                                final SubLObject v_bindings2 = quantities.term_unify_with_units(query_x_value_with_units, answer_x_value_with_units);
                                backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings2, asent), UNPROVIDED, UNPROVIDED), v_bindings2, UNPROVIDED);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list14);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_extrapolation_with_simple_linear_regression_with_error_bars_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return removal_interpolation_or_extrapolation_with_error_bars_with_java_link_function_expand_helper(asent, JAVA_LINK_X_Y_REGRESSION_FIND_Y_FOR_X_WITH_ERROR_BARS);
    }

    public static SubLObject removal_interpolation_or_extrapolation_with_error_bars_with_java_link_function_expand_helper(final SubLObject asent, final SubLObject java_link_function) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject query_point = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject query_error_with_units = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        if (NIL != el_extensional_set_p(set_of_points_in_units)) {
            final SubLObject v_2d_points = extensional_set_elements(set_of_points_in_units);
            if ((NIL != v_2d_points) && (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points, UNPROVIDED))) {
                thread.resetMultipleValues();
                final SubLObject x_y_values = explode_2d_points_with_units(v_2d_points, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                if (((NIL != x_y_values) && (NIL != x_units)) && (NIL != y_units)) {
                    SubLObject current;
                    final SubLObject datum = current = query_point;
                    SubLObject the_2d_point = NIL;
                    SubLObject query_x_value_with_units = NIL;
                    SubLObject query_y_value_with_units = NIL;
                    destructuring_bind_must_consp(current, datum, $list14);
                    the_2d_point = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list14);
                    query_x_value_with_units = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list14);
                    query_y_value_with_units = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL != variables.not_fully_bound_p(query_y_value_with_units)) {
                            thread.resetMultipleValues();
                            final SubLObject query_x_units = quantities.explode_interval(query_x_value_with_units);
                            final SubLObject query_x_value = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            final SubLObject normalized_query_x_value = quantities.convert_to_units_absolute(x_units, query_x_units, query_x_value, UNPROVIDED);
                            thread.resetMultipleValues();
                            final SubLObject answer_y_value = funcall(java_link_function, x_y_values, normalized_query_x_value);
                            final SubLObject answer_y_error = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if ((NIL != answer_y_value) && (NIL != answer_y_error)) {
                                final SubLObject answer_y_value_with_units = quantities.make_interval(y_units, answer_y_value, UNPROVIDED);
                                final SubLObject answer_y_error_with_units = quantities.make_interval(y_units, answer_y_error, UNPROVIDED);
                                final SubLObject v_bindings = append(quantities.term_unify_with_units(query_y_value_with_units, answer_y_value_with_units), quantities.term_unify_with_units(query_error_with_units, answer_y_error_with_units));
                                backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                            }
                        } else {
                            final SubLObject y_x_values = Mapping.mapcar(NREVERSE, x_y_values);
                            thread.resetMultipleValues();
                            final SubLObject query_y_units = quantities.explode_interval(query_y_value_with_units);
                            final SubLObject query_y_value = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            final SubLObject normalized_query_y_value = quantities.convert_to_units_absolute(y_units, query_y_units, query_y_value, UNPROVIDED);
                            thread.resetMultipleValues();
                            final SubLObject answer_x_value = funcall(java_link_function, y_x_values, normalized_query_y_value);
                            final SubLObject answer_x_error = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if ((NIL != answer_x_value) && (NIL != answer_x_error)) {
                                final SubLObject answer_x_value_with_units = quantities.make_interval(x_units, answer_x_value, UNPROVIDED);
                                final SubLObject answer_x_error_with_units = quantities.make_interval(x_units, answer_x_error, UNPROVIDED);
                                final SubLObject v_bindings2 = append(quantities.term_unify_with_units(query_x_value_with_units, answer_x_value_with_units), quantities.term_unify_with_units(query_error_with_units, answer_x_error_with_units));
                                backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings2, asent), UNPROVIDED, UNPROVIDED), v_bindings2, UNPROVIDED);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list14);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_regression_lines_for_sets_intersect_at_point_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_in_units_1 = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject set_of_points_in_units_2 = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject query_point = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        if ((NIL != el_extensional_set_p(set_of_points_in_units_1)) && (NIL != el_extensional_set_p(set_of_points_in_units_2))) {
            final SubLObject v_2d_points_1 = extensional_set_elements(set_of_points_in_units_1);
            final SubLObject v_2d_points_2 = extensional_set_elements(set_of_points_in_units_2);
            if ((NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points_1, UNPROVIDED)) && (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points_2, UNPROVIDED))) {
                thread.resetMultipleValues();
                final SubLObject x_y_values_1 = explode_2d_points_with_units(v_2d_points_1, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                final SubLObject x_y_values_2 = explode_2d_points_with_units(v_2d_points_2, x_units, y_units);
                thread.resetMultipleValues();
                final SubLObject x = java_link_point_intersection_of_two_x_y_regressions(x_y_values_1, x_y_values_2);
                final SubLObject y = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (x.isNumber() && y.isNumber()) {
                    if (NIL != variables.variable_p(query_point)) {
                        final SubLObject x_y_point_with_units = list($$The2DPoint, quantities.make_interval(x_units, x, UNPROVIDED), quantities.make_interval(y_units, y, UNPROVIDED));
                        final SubLObject v_bindings = quantities.term_unify_with_units(query_point, x_y_point_with_units);
                        backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                    } else {
                        final SubLObject v_bindings2 = append(quantities.term_unify_with_units(second(query_point), quantities.make_interval(x_units, x, UNPROVIDED)), quantities.term_unify_with_units(third(query_point), quantities.make_interval(y_units, y, UNPROVIDED)));
                        backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings2, asent), UNPROVIDED, UNPROVIDED), v_bindings2, UNPROVIDED);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_slope_of_regression_line_for_set_of_points_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject query_slope = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        if ((NIL != el_extensional_set_p(set_of_points_in_units)) && ONE_INTEGER.numL(el_set_size(set_of_points_in_units))) {
            final SubLObject v_2d_points = extensional_set_elements(set_of_points_in_units);
            if (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points, UNPROVIDED)) {
                thread.resetMultipleValues();
                final SubLObject x_y_values = explode_2d_points_with_units(v_2d_points, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                final SubLObject slope = java_link_slope_and_intercept_of_x_y_regression(x_y_values);
                final SubLObject bindings_1 = quantities.term_unify_with_units(query_slope, quantities.make_interval(quantities.unit_quotient(y_units, x_units), slope, UNPROVIDED));
                final SubLObject bindings_2 = (NIL != czer_utilities.equals_elP(x_units, y_units, UNPROVIDED, UNPROVIDED, UNPROVIDED)) ? NIL : quantities.term_unify_with_units(query_slope, quantities.make_interval(quantities.unit_quotient(x_units, y_units), divide(ONE_INTEGER, slope), UNPROVIDED));
                if (NIL != bindings_1) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(bindings_1, asent), UNPROVIDED, UNPROVIDED), bindings_1, UNPROVIDED);
                }
                if (NIL != bindings_2) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(bindings_2, asent), UNPROVIDED, UNPROVIDED), bindings_2, UNPROVIDED);
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_set_of_first_derivative_points_based_on_set_of_points_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject function_type = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject set_of_derivative_points_in_units = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        if (NIL != el_extensional_set_p(set_of_points_in_units)) {
            final SubLObject v_2d_points = extensional_set_elements(set_of_points_in_units);
            if (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points, UNPROVIDED)) {
                thread.resetMultipleValues();
                final SubLObject x_y_values = explode_2d_points_with_units(v_2d_points, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                final SubLObject x_y_derivative_points = java_link_x_y_derivatives_for_interpolation_at_each_x(x_y_values, function_type);
                final SubLObject derivative_units = quantities.unit_quotient(y_units, x_units);
                SubLObject result = NIL;
                SubLObject v_bindings = NIL;
                SubLObject cdolist_list_var = x_y_derivative_points;
                SubLObject x_y_derivative_point = NIL;
                x_y_derivative_point = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    result = cons(list($$The2DPoint, list(x_units, x_y_derivative_point.first()), list(derivative_units, second(x_y_derivative_point))), result);
                    cdolist_list_var = cdolist_list_var.rest();
                    x_y_derivative_point = cdolist_list_var.first();
                } 
                result = make_el_extensional_set(nreverse(result));
                v_bindings = unification_utilities.term_unify(result, set_of_derivative_points_in_units, UNPROVIDED, UNPROVIDED);
                if (NIL != v_bindings) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_set_has_points_with_y_value_reln_to_matching_x_value_points_in_set_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject set_of_points_1_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject set_of_points_2_in_units = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject binary_function = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        final SubLObject binary_predicate = cycl_utilities.atomic_sentence_arg4(asent, UNPROVIDED);
        final SubLObject value_in_y_units = cycl_utilities.atomic_sentence_arg5(asent, UNPROVIDED);
        final SubLObject set_of_qualifying_points_in_units = cycl_utilities.atomic_sentence_arg6(asent, UNPROVIDED);
        if (((((NIL != el_extensional_set_p(set_of_points_1_in_units)) && (NIL != el_extensional_set_p(set_of_points_2_in_units))) && (NIL != fort_types_interface.functionP(binary_function))) && arity.arity(binary_function).eql(TWO_INTEGER)) && (NIL != kb_accessors.binary_predicateP(binary_predicate))) {
            final SubLObject v_2d_points_1 = extensional_set_elements(set_of_points_1_in_units);
            final SubLObject v_2d_points_2 = extensional_set_elements(set_of_points_2_in_units);
            if ((NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points_1, UNPROVIDED)) && (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points_2, UNPROVIDED))) {
                thread.resetMultipleValues();
                final SubLObject x_y_values_1 = explode_2d_points_with_units(v_2d_points_1, UNPROVIDED, UNPROVIDED);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                final SubLObject x_y_values_2 = explode_2d_points_with_units(v_2d_points_2, x_units, y_units);
                final SubLObject y_compare_value = second(quantities.cyc_quantity_conversion(y_units, value_in_y_units));
                SubLObject result_x_y_values = NIL;
                SubLObject cdolist_list_var = x_y_values_1;
                SubLObject x_y_value_1 = NIL;
                x_y_value_1 = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject x_value_1 = x_y_value_1.first();
                    final SubLObject y_value_1 = second(x_y_value_1);
                    final SubLObject y_value_2 = second(find(x_value_1, x_y_values_2, EQL, FIRST, UNPROVIDED, UNPROVIDED));
                    if ((NIL != y_value_2) && (NIL != backward.removal_ask(list(binary_predicate, list(binary_function, y_value_1, y_value_2), y_compare_value), UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                        result_x_y_values = cons(x_y_value_1, result_x_y_values);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    x_y_value_1 = cdolist_list_var.first();
                } 
                SubLObject result = NIL;
                SubLObject cdolist_list_var2 = result_x_y_values;
                SubLObject result_x_y_value = NIL;
                result_x_y_value = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    result = cons(list($$The2DPoint, list(x_units, result_x_y_value.first()), list(y_units, second(result_x_y_value))), result);
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    result_x_y_value = cdolist_list_var2.first();
                } 
                result = make_el_extensional_set(nreverse(result));
                final SubLObject v_bindings = unification_utilities.term_unify(result, set_of_qualifying_points_in_units, UNPROVIDED, UNPROVIDED);
                if (NIL != v_bindings) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                }
            }
        }
        return NIL;
    }

    public static SubLObject java_link_debug_modeP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $inference_debugP$.getDynamicValue(thread);
    }

    public static SubLObject java_link_slope_and_intercept_of_x_y_regression(final SubLObject x_y_values) {
        final SubLObject regression = JavaLink.java_new($str35$org_apache_commons_math_stat_regr, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject add_data_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$addData, new SubLObject[]{ $$$double, $$$double });
        final SubLObject slope_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$getSlope, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject intercept_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$getIntercept, EMPTY_SUBL_OBJECT_ARRAY);
        SubLObject cdolist_list_var = x_y_values;
        SubLObject x_y_value = NIL;
        x_y_value = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            JavaLink.java_call(add_data_method, regression, new SubLObject[]{ x_y_value.first(), second(x_y_value) });
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value = cdolist_list_var.first();
        } 
        return values(JavaLink.java_call(slope_method, regression, EMPTY_SUBL_OBJECT_ARRAY), JavaLink.java_call(intercept_method, regression, EMPTY_SUBL_OBJECT_ARRAY));
    }

    public static SubLObject java_link_x_y_cubic_spline_find_y_for_x(SubLObject x_y_values, final SubLObject x_value) {
        if ((x_value.numL(apply(MIN, Mapping.mapcar(FIRST, x_y_values))) || x_value.numG(apply(MAX, Mapping.mapcar(FIRST, x_y_values)))) || ((NIL == java_link_debug_modeP()) && (NIL != list_utilities.duplicatesP(Mapping.mapcar(FIRST, x_y_values), UNPROVIDED, UNPROVIDED)))) {
            return NIL;
        }
        final SubLObject interpolator = JavaLink.java_new($str42$org_apache_commons_math_analysis_, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject interpolate_method = JavaLink.java_method($str42$org_apache_commons_math_analysis_, $$$interpolate, new SubLObject[]{ $str44$_D, $str44$_D });
        final SubLObject value_method = JavaLink.java_method($str45$org_apache_commons_math_analysis_, $$$value, new SubLObject[]{ $$$double });
        x_y_values = Sort.sort(x_y_values, $sym47$_, FIRST);
        final SubLObject poly_spline_function = JavaLink.java_call(interpolate_method, interpolator, new SubLObject[]{ Mapping.mapcar(FIRST, x_y_values), Mapping.mapcar(SECOND, x_y_values) });
        return JavaLink.java_call(value_method, poly_spline_function, new SubLObject[]{ x_value });
    }

    public static SubLObject java_link_x_y_piecewise_linear_find_y_for_x(SubLObject x_y_values, final SubLObject x_value) {
        if ((x_value.numL(apply(MIN, Mapping.mapcar(FIRST, x_y_values))) || x_value.numG(apply(MAX, Mapping.mapcar(FIRST, x_y_values)))) || ((NIL == java_link_debug_modeP()) && (NIL != list_utilities.duplicatesP(Mapping.mapcar(FIRST, x_y_values), UNPROVIDED, UNPROVIDED)))) {
            return NIL;
        }
        final SubLObject interpolator = JavaLink.java_new($str49$org_apache_commons_math_analysis_, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject interpolate_method = JavaLink.java_method($str49$org_apache_commons_math_analysis_, $$$interpolate, new SubLObject[]{ $str44$_D, $str44$_D });
        final SubLObject value_method = JavaLink.java_method($str45$org_apache_commons_math_analysis_, $$$value, new SubLObject[]{ $$$double });
        x_y_values = Sort.sort(x_y_values, $sym47$_, FIRST);
        final SubLObject poly_spline_function = JavaLink.java_call(interpolate_method, interpolator, new SubLObject[]{ Mapping.mapcar(FIRST, x_y_values), Mapping.mapcar(SECOND, x_y_values) });
        return JavaLink.java_call(value_method, poly_spline_function, new SubLObject[]{ x_value });
    }

    public static SubLObject java_link_x_y_regression_find_y_for_x(final SubLObject x_y_values, final SubLObject x_value) {
        final SubLObject regression = JavaLink.java_new($str35$org_apache_commons_math_stat_regr, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject add_data_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$addData, new SubLObject[]{ $$$double, $$$double });
        final SubLObject predict_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$predict, new SubLObject[]{ $$$double });
        SubLObject cdolist_list_var = x_y_values;
        SubLObject x_y_value = NIL;
        x_y_value = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            JavaLink.java_call(add_data_method, regression, new SubLObject[]{ x_y_value.first(), second(x_y_value) });
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value = cdolist_list_var.first();
        } 
        return JavaLink.java_call(predict_method, regression, new SubLObject[]{ x_value });
    }

    public static SubLObject java_link_x_y_regression_find_y_for_x_with_error_bars(final SubLObject x_y_values, final SubLObject x_value) {
        if (length(x_y_values).numL(THREE_INTEGER)) {
            return NIL;
        }
        final SubLObject number_of_points = length(x_y_values);
        final SubLObject regression = JavaLink.java_new($str35$org_apache_commons_math_stat_regr, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject add_data_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$addData, new SubLObject[]{ $$$double, $$$double });
        final SubLObject sse_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$getSumSquaredErrors, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject predict_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$predict, new SubLObject[]{ $$$double });
        SubLObject cdolist_list_var = x_y_values;
        SubLObject x_y_value = NIL;
        x_y_value = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            JavaLink.java_call(add_data_method, regression, new SubLObject[]{ x_y_value.first(), second(x_y_value) });
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value = cdolist_list_var.first();
        } 
        final SubLObject sum_of_squared_errors = JavaLink.java_call(sse_method, regression, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject y_error = sqrt(divide(sum_of_squared_errors, subtract(number_of_points, TWO_INTEGER)));
        return values(JavaLink.java_call(predict_method, regression, new SubLObject[]{ x_value }), y_error);
    }

    public static SubLObject java_link_point_intersection_of_two_x_y_regressions(final SubLObject x_y_values_1, final SubLObject x_y_values_2) {
        if ((((NIL == x_y_values_1) || (NIL != list_utilities.singletonP(x_y_values_1))) || (NIL == x_y_values_2)) || (NIL != list_utilities.singletonP(x_y_values_2))) {
            return NIL;
        }
        final SubLObject regression_1 = JavaLink.java_new($str35$org_apache_commons_math_stat_regr, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject regression_2 = JavaLink.java_new($str35$org_apache_commons_math_stat_regr, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject add_data_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$addData, new SubLObject[]{ $$$double, $$$double });
        final SubLObject predict_method = JavaLink.java_method($str35$org_apache_commons_math_stat_regr, $$$predict, new SubLObject[]{ $$$double });
        final SubLObject l1_p1_x = x_y_values_1.first().first();
        final SubLObject l1_p2_x = second(x_y_values_1).first();
        final SubLObject l2_p1_x = x_y_values_2.first().first();
        final SubLObject l2_p2_x = second(x_y_values_2).first();
        SubLObject cdolist_list_var = x_y_values_1;
        SubLObject x_y_value_1 = NIL;
        x_y_value_1 = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            JavaLink.java_call(add_data_method, regression_1, new SubLObject[]{ x_y_value_1.first(), second(x_y_value_1) });
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value_1 = cdolist_list_var.first();
        } 
        cdolist_list_var = x_y_values_2;
        SubLObject x_y_value_2 = NIL;
        x_y_value_2 = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            JavaLink.java_call(add_data_method, regression_2, new SubLObject[]{ x_y_value_2.first(), second(x_y_value_2) });
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value_2 = cdolist_list_var.first();
        } 
        final SubLObject l1_p1_y = JavaLink.java_call(predict_method, regression_1, new SubLObject[]{ l1_p1_x });
        final SubLObject l1_p2_y = JavaLink.java_call(predict_method, regression_1, new SubLObject[]{ l1_p2_x });
        final SubLObject l2_p1_y = JavaLink.java_call(predict_method, regression_2, new SubLObject[]{ l2_p1_x });
        final SubLObject l2_p2_y = JavaLink.java_call(predict_method, regression_2, new SubLObject[]{ l2_p2_x });
        final SubLObject line_class_name = $str52$org_apache_commons_math_geometry_;
        final SubLObject vector2d_class_name = $str53$org_apache_commons_math_geometry_;
        final SubLObject line_new_method = JavaLink.java_method(line_class_name, NIL, new SubLObject[]{ vector2d_class_name, vector2d_class_name });
        final SubLObject vector2d_new_method = JavaLink.java_method(vector2d_class_name, NIL, new SubLObject[]{ $$$double, $$$double });
        final SubLObject line_1 = JavaLink.java_new(line_new_method, new SubLObject[]{ JavaLink.java_new(vector2d_new_method, new SubLObject[]{ l1_p1_x, l1_p1_y }), JavaLink.java_new(vector2d_new_method, new SubLObject[]{ l1_p2_x, l1_p2_y }) });
        final SubLObject line_2 = JavaLink.java_new(line_new_method, new SubLObject[]{ JavaLink.java_new(vector2d_new_method, new SubLObject[]{ l2_p1_x, l2_p1_y }), JavaLink.java_new(vector2d_new_method, new SubLObject[]{ l2_p2_x, l2_p2_y }) });
        final SubLObject intersection_method = JavaLink.java_method(line_class_name, $$$intersection, new SubLObject[]{ $str55$org_apache_commons_math_geometry_ });
        final SubLObject intersection_2dvector = JavaLink.java_call(intersection_method, line_1, new SubLObject[]{ line_2 });
        if (NIL != intersection_2dvector) {
            final SubLObject vector2d_getx_method = JavaLink.java_method(vector2d_class_name, $$$getX, EMPTY_SUBL_OBJECT_ARRAY);
            final SubLObject vector2d_gety_method = JavaLink.java_method(vector2d_class_name, $$$getY, EMPTY_SUBL_OBJECT_ARRAY);
            return values(JavaLink.java_call(vector2d_getx_method, intersection_2dvector, EMPTY_SUBL_OBJECT_ARRAY), JavaLink.java_call(vector2d_gety_method, intersection_2dvector, EMPTY_SUBL_OBJECT_ARRAY));
        }
        return NIL;
    }

    public static SubLObject java_link_x_y_derivatives_for_interpolation_at_each_x(SubLObject x_y_values, final SubLObject interpolator) {
        if ((NIL == java_link_debug_modeP()) && (NIL != list_utilities.duplicatesP(Mapping.mapcar(FIRST, x_y_values), UNPROVIDED, UNPROVIDED))) {
            return NIL;
        }
        final SubLObject interpolator_string = (interpolator.eql($$CubicSpline)) ? $str42$org_apache_commons_math_analysis_ : interpolator.eql($const59$LinearPolynomialFunction_Piecewis) ? $str49$org_apache_commons_math_analysis_ : $str42$org_apache_commons_math_analysis_;
        final SubLObject interpolator_$1 = JavaLink.java_new(interpolator_string, EMPTY_SUBL_OBJECT_ARRAY);
        final SubLObject interpolate_method = JavaLink.java_method(interpolator_string, $$$interpolate, new SubLObject[]{ $str44$_D, $str44$_D });
        final SubLObject value_method = JavaLink.java_method($str45$org_apache_commons_math_analysis_, $$$value, new SubLObject[]{ $$$double });
        final SubLObject derivative_method = JavaLink.java_method($str45$org_apache_commons_math_analysis_, $$$derivative, EMPTY_SUBL_OBJECT_ARRAY);
        SubLObject derivative_x_y_values = NIL;
        x_y_values = Sort.sort(copy_list(x_y_values), $sym47$_, FIRST);
        final SubLObject poly_spline_function = JavaLink.java_call(interpolate_method, interpolator_$1, new SubLObject[]{ Mapping.mapcar(FIRST, x_y_values), Mapping.mapcar(SECOND, x_y_values) });
        final SubLObject poly_spline_derivative = JavaLink.java_call(derivative_method, poly_spline_function, EMPTY_SUBL_OBJECT_ARRAY);
        SubLObject cdolist_list_var = x_y_values;
        SubLObject x_y_value = NIL;
        x_y_value = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject x_value = x_y_value.first();
            derivative_x_y_values = cons(list(x_value, JavaLink.java_call(value_method, poly_spline_derivative, new SubLObject[]{ x_value })), derivative_x_y_values);
            cdolist_list_var = cdolist_list_var.rest();
            x_y_value = cdolist_list_var.first();
        } 
        return nreverse(derivative_x_y_values);
    }

    public static SubLObject el_2d_point_p(final SubLObject v_object) {
        return makeBoolean(((NIL != el_formula_p(v_object)) && cycl_utilities.formula_operator(v_object).eql($$The2DPoint)) && (NIL != el_binary_formula_p(v_object)));
    }

    public static SubLObject explode_2d_points_with_units(final SubLObject v_2d_points, SubLObject x_units, SubLObject y_units) {
        if (x_units == UNPROVIDED) {
            x_units = NIL;
        }
        if (y_units == UNPROVIDED) {
            y_units = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != list_utilities.non_dotted_list_p(v_2d_points) : "list_utilities.non_dotted_list_p(v_2d_points) " + "CommonSymbols.NIL != list_utilities.non_dotted_list_p(v_2d_points) " + v_2d_points;
        SubLObject cdolist_list_var = v_2d_points;
        SubLObject elem = NIL;
        elem = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            assert NIL != el_2d_point_p(elem) : "removal_modules_math.el_2d_point_p(elem) " + "CommonSymbols.NIL != removal_modules_math.el_2d_point_p(elem) " + elem;
            cdolist_list_var = cdolist_list_var.rest();
            elem = cdolist_list_var.first();
        } 
        SubLObject x_y_values = NIL;
        cdolist_list_var = v_2d_points;
        SubLObject v_2d_point = NIL;
        v_2d_point = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject x_y_with_units = cycl_utilities.formula_args(v_2d_point, UNPROVIDED);
            final SubLObject x_with_units = x_y_with_units.first();
            final SubLObject y_with_units = second(x_y_with_units);
            thread.resetMultipleValues();
            final SubLObject this_x_units = quantities.explode_interval(x_with_units);
            SubLObject this_x_value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL == x_units) {
                x_units = this_x_units;
            }
            if (!x_units.equal(this_x_units)) {
                this_x_value = quantities.convert_to_units_absolute(x_units, this_x_units, this_x_value, UNPROVIDED);
            }
            thread.resetMultipleValues();
            final SubLObject this_y_units = quantities.explode_interval(y_with_units);
            SubLObject this_y_value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL == y_units) {
                y_units = this_y_units;
            }
            if (!y_units.equal(this_y_units)) {
                this_y_value = quantities.convert_to_units_absolute(y_units, this_y_units, this_y_value, UNPROVIDED);
            }
            x_y_values = cons(list(this_x_value, this_y_value), x_y_values);
            cdolist_list_var = cdolist_list_var.rest();
            v_2d_point = cdolist_list_var.first();
        } 
        return values(x_y_values, x_units, y_units);
    }

    public static SubLObject removal_set_has_points_with_y_value_reln_to_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject operator = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject comparator = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        final SubLObject quant1 = cycl_utilities.atomic_sentence_arg4(asent, UNPROVIDED);
        final SubLObject quant2 = cycl_utilities.atomic_sentence_arg5(asent, UNPROVIDED);
        final SubLObject newset_var = cycl_utilities.atomic_sentence_arg6(asent, UNPROVIDED);
        if ((((NIL != el_extensional_set_p(set_of_points_in_units)) && (NIL != fort_types_interface.functionP(operator))) && (NIL != relation_evaluation.evaluatable_predicateP(comparator, UNPROVIDED))) && arity.arity(comparator).eql(TWO_INTEGER)) {
            final SubLObject x_y_values_with_units = extensional_set_elements(set_of_points_in_units);
            SubLObject result_points = NIL;
            if (NIL != list_utilities.every_in_list(EL_2D_POINT_P, x_y_values_with_units, UNPROVIDED)) {
                SubLObject cdolist_list_var = x_y_values_with_units;
                SubLObject x_y_value_with_units = NIL;
                x_y_value_with_units = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject y_value = cycl_utilities.formula_arg2(x_y_value_with_units, UNPROVIDED);
                    if (NIL != relation_evaluation.cyc_evaluate(list(comparator, list(operator, y_value, quant1), quant2))) {
                        result_points = cons(x_y_value_with_units, result_points);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    x_y_value_with_units = cdolist_list_var.first();
                } 
            }
            if (NIL != result_points) {
                final SubLObject v_bindings = unification_utilities.term_unify(make_el_set(list_utilities.fast_remove_duplicates(result_points, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED), newset_var, UNPROVIDED, UNPROVIDED);
                if (NIL != v_bindings) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_operation_result_comparison_pos_check(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject operator = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject a = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject b = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        final SubLObject comparator = cycl_utilities.atomic_sentence_arg4(asent, UNPROVIDED);
        final SubLObject quant = cycl_utilities.atomic_sentence_arg5(asent, UNPROVIDED);
        if (((NIL != fort_types_interface.functionP(operator)) && (NIL != relation_evaluation.evaluatable_predicateP(comparator, UNPROVIDED))) && arity.arity(comparator).eql(TWO_INTEGER)) {
            final SubLObject expression = list(comparator, list(operator, a, b), quant);
            if (NIL != relation_evaluation.cyc_evaluate(expression)) {
                backward.removal_add_node(arguments.make_hl_support($OPAQUE, asent, UNPROVIDED, UNPROVIDED), NIL, list(removal_modules_evaluation.make_eval_support(expression, UNPROVIDED)));
            }
        }
        return NIL;
    }

    public static SubLObject removal_operation_result_comparison_neg_check(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject operator = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject a = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject b = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        final SubLObject comparator = cycl_utilities.atomic_sentence_arg4(asent, UNPROVIDED);
        final SubLObject quant = cycl_utilities.atomic_sentence_arg5(asent, UNPROVIDED);
        if (((NIL != fort_types_interface.functionP(operator)) && (NIL != relation_evaluation.evaluatable_predicateP(comparator, UNPROVIDED))) && arity.arity(comparator).eql(TWO_INTEGER)) {
            final SubLObject expression = list(comparator, list(operator, a, b), quant);
            if (NIL == relation_evaluation.cyc_evaluate(expression)) {
                backward.removal_add_node(arguments.make_hl_support($OPAQUE, make_negation(asent), UNPROVIDED, UNPROVIDED), NIL, list(removal_modules_evaluation.make_eval_support(make_negation(expression), UNPROVIDED)));
            }
        }
        return NIL;
    }

    public static SubLObject removal_inflow_lengths_with_starting_depths_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject well = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject set_of_points_in_units = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject function_type = $$CubicSpline;
        final SubLObject length_and_depths_query = cycl_utilities.atomic_sentence_arg3(asent, UNPROVIDED);
        final SubLObject debugP = NIL;
        if (NIL != el_extensional_set_p(set_of_points_in_units)) {
            final SubLObject v_2d_points = extensional_set_elements(set_of_points_in_units);
            if (NIL != list_utilities.every_in_list(EL_2D_POINT_P, v_2d_points, UNPROVIDED)) {
                thread.resetMultipleValues();
                SubLObject x_y_values = explode_2d_points_with_units(v_2d_points, $$Meter, $$DegreeCelsius);
                final SubLObject x_units = thread.secondMultipleValue();
                final SubLObject y_units = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                x_y_values = Sort.sort(copy_list(x_y_values), $sym47$_, FIRST);
                final SubLObject x_y_derivative_points = java_link_x_y_derivatives_for_interpolation_at_each_x(x_y_values, function_type);
                final SubLObject average_y_value = number_utilities.average(x_y_values, SECOND);
                final SubLObject derivative_units = quantities.unit_quotient(y_units, x_units);
                SubLObject inflow_lengths = NIL;
                if (NIL != debugP) {
                    print(x_y_values, UNPROVIDED);
                    print(x_y_derivative_points, UNPROVIDED);
                    format_nil.force_format(T, $str76$____, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                SubLObject cdolist_list_var = Sort.sort(copy_list(x_y_values), $sym85$_, SECOND);
                SubLObject x_y_value = NIL;
                x_y_value = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = x_y_value;
                    SubLObject x_value = NIL;
                    SubLObject y_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list77);
                    x_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list77);
                    y_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL != debugP) {
                            format_nil.force_format(T, $str78$High_value__x__A_y__A__, x_value, y_value, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        }
                        if (y_value.numG(add(FIVE_INTEGER, average_y_value))) {
                            SubLObject already_in_inflowP = NIL;
                            SubLObject inflow_start_x = NIL;
                            SubLObject inflow_end_x = NIL;
                            if (NIL == already_in_inflowP) {
                                SubLObject csome_list_var = inflow_lengths;
                                SubLObject inflow_length = NIL;
                                inflow_length = csome_list_var.first();
                                while ((NIL == already_in_inflowP) && (NIL != csome_list_var)) {
                                    if (x_value.numGE(inflow_length.first()) && x_value.numLE(second(inflow_length))) {
                                        already_in_inflowP = T;
                                    }
                                    csome_list_var = csome_list_var.rest();
                                    inflow_length = csome_list_var.first();
                                } 
                            }
                            if (NIL == already_in_inflowP) {
                                SubLObject found_start_pointP = NIL;
                                if (NIL == found_start_pointP) {
                                    SubLObject cur_x_y_value = NIL;
                                    SubLObject cur_x_y_value_$2 = NIL;
                                    SubLObject cur_x_y_derivative_point = NIL;
                                    SubLObject cur_x_y_derivative_point_$3 = NIL;
                                    cur_x_y_value = nreverse(copy_list(x_y_values));
                                    cur_x_y_value_$2 = cur_x_y_value.first();
                                    cur_x_y_derivative_point = nreverse(copy_list(x_y_derivative_points));
                                    cur_x_y_derivative_point_$3 = cur_x_y_derivative_point.first();
                                    while ((NIL == found_start_pointP) && ((NIL != cur_x_y_derivative_point) || (NIL != cur_x_y_value))) {
                                        SubLObject current_$5;
                                        final SubLObject datum_$4 = current_$5 = cur_x_y_derivative_point_$3;
                                        SubLObject cur_x_value = NIL;
                                        SubLObject cur_y_derivative_value = NIL;
                                        destructuring_bind_must_consp(current_$5, datum_$4, $list79);
                                        cur_x_value = current_$5.first();
                                        current_$5 = current_$5.rest();
                                        destructuring_bind_must_consp(current_$5, datum_$4, $list79);
                                        cur_y_derivative_value = current_$5.first();
                                        current_$5 = current_$5.rest();
                                        if (NIL == current_$5) {
                                            final SubLObject cur_y_value = second(cur_x_y_value_$2);
                                            if (NIL != debugP) {
                                                format_nil.force_format(T, $str80$checking_x__A_deriv__A__, cur_x_value, cur_y_derivative_value, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                if (!cur_x_value.eql(cur_x_y_value_$2.first())) {
                                                    print($$$Bad_looping, UNPROVIDED);
                                                    Errors.sublisp_break(UNPROVIDED, EMPTY_SUBL_OBJECT_ARRAY);
                                                }
                                            }
                                            if (cur_y_derivative_value.numGE($float$0_5) && cur_x_value.numL(x_value)) {
                                                found_start_pointP = T;
                                                inflow_start_x = cur_x_value;
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum_$4, $list79);
                                        }
                                        cur_x_y_value = cur_x_y_value.rest();
                                        cur_x_y_value_$2 = cur_x_y_value.first();
                                        cur_x_y_derivative_point = cur_x_y_derivative_point.rest();
                                        cur_x_y_derivative_point_$3 = cur_x_y_derivative_point.first();
                                    } 
                                }
                                SubLObject found_end_pointP = NIL;
                                if (NIL == found_end_pointP) {
                                    SubLObject cur_x_y_value = NIL;
                                    SubLObject cur_x_y_value_$3 = NIL;
                                    SubLObject cur_x_y_derivative_point = NIL;
                                    SubLObject cur_x_y_derivative_point_$4 = NIL;
                                    cur_x_y_value = x_y_values;
                                    cur_x_y_value_$3 = cur_x_y_value.first();
                                    cur_x_y_derivative_point = x_y_derivative_points;
                                    cur_x_y_derivative_point_$4 = cur_x_y_derivative_point.first();
                                    while ((NIL == found_end_pointP) && ((NIL != cur_x_y_derivative_point) || (NIL != cur_x_y_value))) {
                                        SubLObject current_$6;
                                        final SubLObject datum_$5 = current_$6 = cur_x_y_derivative_point_$4;
                                        SubLObject cur_x_value = NIL;
                                        SubLObject cur_y_derivative_value = NIL;
                                        destructuring_bind_must_consp(current_$6, datum_$5, $list79);
                                        cur_x_value = current_$6.first();
                                        current_$6 = current_$6.rest();
                                        destructuring_bind_must_consp(current_$6, datum_$5, $list79);
                                        cur_y_derivative_value = current_$6.first();
                                        current_$6 = current_$6.rest();
                                        if (NIL == current_$6) {
                                            final SubLObject cur_y_value = second(cur_x_y_value_$3);
                                            if (NIL != debugP) {
                                                format_nil.force_format(T, $str80$checking_x__A_deriv__A__, cur_x_value, cur_y_derivative_value, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                if (!cur_x_value.eql(cur_x_y_value_$3.first())) {
                                                    print($$$Bad_looping, UNPROVIDED);
                                                    Errors.sublisp_break(UNPROVIDED, EMPTY_SUBL_OBJECT_ARRAY);
                                                }
                                            }
                                            if (cur_y_derivative_value.numLE($float$_0_5) && cur_x_value.numG(x_value)) {
                                                found_end_pointP = T;
                                                inflow_end_x = cur_x_value;
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum_$5, $list79);
                                        }
                                        cur_x_y_value = cur_x_y_value.rest();
                                        cur_x_y_value_$3 = cur_x_y_value.first();
                                        cur_x_y_derivative_point = cur_x_y_derivative_point.rest();
                                        cur_x_y_derivative_point_$4 = cur_x_y_derivative_point.first();
                                    } 
                                }
                            }
                            if ((NIL != inflow_start_x) && (NIL != inflow_end_x)) {
                                if (NIL != debugP) {
                                    format_nil.force_format(T, $str84$Got_interval___A__A___, inflow_start_x, inflow_end_x, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                }
                                inflow_lengths = cons(list(inflow_start_x, inflow_end_x), inflow_lengths);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list77);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    x_y_value = cdolist_list_var.first();
                } 
                inflow_lengths = Sort.sort(inflow_lengths, $sym47$_, FIRST);
                if (NIL != debugP) {
                    format_nil.force_format(T, $str86$inflow_lengths___A__, inflow_lengths, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                SubLObject merged_inflow_lengths = NIL;
                SubLObject cur_start = NIL;
                SubLObject cur_end = NIL;
                SubLObject cdolist_list_var2 = inflow_lengths;
                SubLObject inflow_length2 = NIL;
                inflow_length2 = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    SubLObject current2;
                    final SubLObject datum2 = current2 = inflow_length2;
                    SubLObject start = NIL;
                    SubLObject end = NIL;
                    destructuring_bind_must_consp(current2, datum2, $list87);
                    start = current2.first();
                    current2 = current2.rest();
                    destructuring_bind_must_consp(current2, datum2, $list87);
                    end = current2.first();
                    current2 = current2.rest();
                    if (NIL == current2) {
                        if ((NIL != cur_start) && (NIL != cur_end)) {
                            if (start.numLE(cur_end)) {
                                cur_end = end;
                            } else {
                                merged_inflow_lengths = cons(list(cur_start, cur_end), merged_inflow_lengths);
                                cur_start = start;
                                cur_end = end;
                            }
                        } else {
                            cur_start = start;
                            cur_end = end;
                        }
                    } else {
                        cdestructuring_bind_error(datum2, $list87);
                    }
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    inflow_length2 = cdolist_list_var2.first();
                } 
                if ((NIL != cur_start) && (NIL != cur_end)) {
                    merged_inflow_lengths = cons(list(cur_start, cur_end), merged_inflow_lengths);
                }
                if (NIL != debugP) {
                    format_nil.force_format(T, $str88$merged_inflow_lengths___A__, merged_inflow_lengths, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                inflow_lengths = merged_inflow_lengths;
                SubLObject result = NIL;
                SubLObject cdolist_list_var3 = inflow_lengths;
                SubLObject result_inflow_length = NIL;
                result_inflow_length = cdolist_list_var3.first();
                while (NIL != cdolist_list_var3) {
                    result = cons(list($$TheList, list(x_units, result_inflow_length.first()), list(x_units, subtract(second(result_inflow_length), result_inflow_length.first()))), result);
                    cdolist_list_var3 = cdolist_list_var3.rest();
                    result_inflow_length = cdolist_list_var3.first();
                } 
                result = make_el_list(result, UNPROVIDED);
                final SubLObject v_bindings = unification_utilities.term_unify(result, length_and_depths_query, UNPROVIDED, UNPROVIDED);
                if (NIL != v_bindings) {
                    backward.removal_add_node(arguments.make_hl_support($OPAQUE, bindings.apply_bindings(v_bindings, asent), UNPROVIDED, UNPROVIDED), v_bindings, UNPROVIDED);
                }
            }
        }
        return NIL;
    }

    public static SubLObject declare_removal_modules_math_file() {
        declareFunction("removal_interpolation_with_cubic_spline_expand", "REMOVAL-INTERPOLATION-WITH-CUBIC-SPLINE-EXPAND", 1, 1, false);
        declareFunction("removal_interpolation_with_piecewise_linear_polynomial_expand", "REMOVAL-INTERPOLATION-WITH-PIECEWISE-LINEAR-POLYNOMIAL-EXPAND", 1, 1, false);
        declareFunction("removal_extrapolation_with_simple_linear_regression_expand", "REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION-EXPAND", 1, 1, false);
        declareFunction("removal_interpolation_or_extrapolation_with_java_link_function_expand_helper", "REMOVAL-INTERPOLATION-OR-EXTRAPOLATION-WITH-JAVA-LINK-FUNCTION-EXPAND-HELPER", 2, 0, false);
        declareFunction("removal_extrapolation_with_simple_linear_regression_with_error_bars_expand", "REMOVAL-EXTRAPOLATION-WITH-SIMPLE-LINEAR-REGRESSION-WITH-ERROR-BARS-EXPAND", 1, 1, false);
        declareFunction("removal_interpolation_or_extrapolation_with_error_bars_with_java_link_function_expand_helper", "REMOVAL-INTERPOLATION-OR-EXTRAPOLATION-WITH-ERROR-BARS-WITH-JAVA-LINK-FUNCTION-EXPAND-HELPER", 2, 0, false);
        declareFunction("removal_regression_lines_for_sets_intersect_at_point_expand", "REMOVAL-REGRESSION-LINES-FOR-SETS-INTERSECT-AT-POINT-EXPAND", 1, 1, false);
        declareFunction("removal_slope_of_regression_line_for_set_of_points_expand", "REMOVAL-SLOPE-OF-REGRESSION-LINE-FOR-SET-OF-POINTS-EXPAND", 1, 1, false);
        declareFunction("removal_set_of_first_derivative_points_based_on_set_of_points_expand", "REMOVAL-SET-OF-FIRST-DERIVATIVE-POINTS-BASED-ON-SET-OF-POINTS-EXPAND", 1, 1, false);
        declareFunction("removal_set_has_points_with_y_value_reln_to_matching_x_value_points_in_set_expand", "REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO-MATCHING-X-VALUE-POINTS-IN-SET-EXPAND", 1, 1, false);
        declareFunction("java_link_debug_modeP", "JAVA-LINK-DEBUG-MODE?", 0, 0, false);
        declareFunction("java_link_slope_and_intercept_of_x_y_regression", "JAVA-LINK-SLOPE-AND-INTERCEPT-OF-X-Y-REGRESSION", 1, 0, false);
        declareFunction("java_link_x_y_cubic_spline_find_y_for_x", "JAVA-LINK-X-Y-CUBIC-SPLINE-FIND-Y-FOR-X", 2, 0, false);
        declareFunction("java_link_x_y_piecewise_linear_find_y_for_x", "JAVA-LINK-X-Y-PIECEWISE-LINEAR-FIND-Y-FOR-X", 2, 0, false);
        declareFunction("java_link_x_y_regression_find_y_for_x", "JAVA-LINK-X-Y-REGRESSION-FIND-Y-FOR-X", 2, 0, false);
        declareFunction("java_link_x_y_regression_find_y_for_x_with_error_bars", "JAVA-LINK-X-Y-REGRESSION-FIND-Y-FOR-X-WITH-ERROR-BARS", 2, 0, false);
        declareFunction("java_link_point_intersection_of_two_x_y_regressions", "JAVA-LINK-POINT-INTERSECTION-OF-TWO-X-Y-REGRESSIONS", 2, 0, false);
        declareFunction("java_link_x_y_derivatives_for_interpolation_at_each_x", "JAVA-LINK-X-Y-DERIVATIVES-FOR-INTERPOLATION-AT-EACH-X", 2, 0, false);
        declareFunction("el_2d_point_p", "EL-2D-POINT-P", 1, 0, false);
        declareFunction("explode_2d_points_with_units", "EXPLODE-2D-POINTS-WITH-UNITS", 1, 2, false);
        declareFunction("removal_set_has_points_with_y_value_reln_to_expand", "REMOVAL-SET-HAS-POINTS-WITH-Y-VALUE-RELN-TO-EXPAND", 1, 1, false);
        declareFunction("removal_operation_result_comparison_pos_check", "REMOVAL-OPERATION-RESULT-COMPARISON-POS-CHECK", 1, 1, false);
        declareFunction("removal_operation_result_comparison_neg_check", "REMOVAL-OPERATION-RESULT-COMPARISON-NEG-CHECK", 1, 1, false);
        declareFunction("removal_inflow_lengths_with_starting_depths_expand", "REMOVAL-INFLOW-LENGTHS-WITH-STARTING-DEPTHS-EXPAND", 1, 1, false);
        return NIL;
    }

    public static SubLObject init_removal_modules_math_file() {
        defparameter("*DEFAULT-MATH-MODULE-COST*", $typical_hl_module_check_cost$.getGlobalValue());
        return NIL;
    }

    public static SubLObject setup_removal_modules_math_file() {
        inference_modules.register_solely_specific_removal_module_predicate($$interpolationWithCubicSpline);
        preference_modules.doomed_unless_arg_bindable($POS, $$interpolationWithCubicSpline, ONE_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_INTERPOLATION_WITH_CUBIC_SPLINE, $list3);
        inference_modules.register_solely_specific_removal_module_predicate($const5$interpolationWithPiecewiseLinearP);
        preference_modules.doomed_unless_arg_bindable($POS, $const5$interpolationWithPiecewiseLinearP, ONE_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_INTERPOLATION_WITH_PIECEWISE_LINEAR_POLYNOMIAL, $list7);
        inference_modules.register_solely_specific_removal_module_predicate($const9$extrapolationWithSimpleLinearRegr);
        preference_modules.doomed_unless_arg_bindable($POS, $const9$extrapolationWithSimpleLinearRegr, ONE_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_EXTRAPOLATION_WITH_SIMPLE_LINEAR_REGRESSION, $list11);
        inference_modules.register_solely_specific_removal_module_predicate($const17$extrapolationWithSimpleLinearRegr);
        preference_modules.doomed_unless_arg_bindable($POS, $const17$extrapolationWithSimpleLinearRegr, ONE_INTEGER);
        inference_modules.inference_removal_module($kw18$REMOVAL_EXTRAPOLATION_WITH_SIMPLE_LINEAR_REGRESSION_WITH_ERROR_BA, $list19);
        inference_modules.register_solely_specific_removal_module_predicate($const21$regressionLinesForSetsIntersectAt);
        preference_modules.doomed_unless_arg_bindable($POS, $const21$regressionLinesForSetsIntersectAt, ONE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const21$regressionLinesForSetsIntersectAt, TWO_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_REGRESSION_LINES_FOR_SETS_INTERSECT_AT_POINT, $list23);
        inference_modules.register_solely_specific_removal_module_predicate($const25$slopeOfRegressionLineForSetOfPoin);
        preference_modules.doomed_unless_arg_bindable($POS, $const25$slopeOfRegressionLineForSetOfPoin, ONE_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_SLOPE_OF_REGRESSION_LINE_FOR_SET_OF_POINTS, $list27);
        inference_modules.register_solely_specific_removal_module_predicate($const28$setOfFirstDerivativePointsBasedOn);
        preference_modules.doomed_unless_arg_bindable($POS, $const28$setOfFirstDerivativePointsBasedOn, ONE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const28$setOfFirstDerivativePointsBasedOn, TWO_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_SET_OF_FIRST_DERIVATIVE_POINTS_BASED_ON_SET_OF_POINTS, $list30);
        inference_modules.register_solely_specific_removal_module_predicate($const31$setHasPointsWithYValueRelnToMatch);
        preference_modules.doomed_unless_arg_bindable($POS, $const31$setHasPointsWithYValueRelnToMatch, ONE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const31$setHasPointsWithYValueRelnToMatch, TWO_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const31$setHasPointsWithYValueRelnToMatch, THREE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const31$setHasPointsWithYValueRelnToMatch, FOUR_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $const31$setHasPointsWithYValueRelnToMatch, FIVE_INTEGER);
        inference_modules.inference_removal_module($kw32$REMOVAL_SET_HAS_POINTS_WITH_Y_VALUE_RELN_TO_MATCHING_X_VALUE_POIN, $list33);
        inference_modules.register_solely_specific_removal_module_predicate($$setHasPointsWithYValueRelnTo);
        preference_modules.doomed_unless_arg_bindable($POS, $$setHasPointsWithYValueRelnTo, ONE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $$setHasPointsWithYValueRelnTo, TWO_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $$setHasPointsWithYValueRelnTo, THREE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $$setHasPointsWithYValueRelnTo, FOUR_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $$setHasPointsWithYValueRelnTo, FIVE_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_SET_HAS_POINTS_WITH_Y_VALUE_RELN_TO, $list64);
        inference_modules.register_solely_specific_removal_module_predicate($$operationResultComparison);
        preference_modules.doomed_unless_all_args_bindable($POS, $$operationResultComparison);
        preference_modules.doomed_unless_all_args_bindable($NEG, $$operationResultComparison);
        inference_modules.inference_removal_module($REMOVAL_OPERATION_RESULT_COMPARISON_POS_CHECK, $list68);
        inference_modules.inference_removal_module($REMOVAL_OPERATION_RESULT_COMPARISON_NEG_CHECK, $list70);
        inference_modules.register_solely_specific_removal_module_predicate($$inflowLengthsWithStartingDepths);
        preference_modules.doomed_unless_arg_bindable($POS, $$inflowLengthsWithStartingDepths, ONE_INTEGER);
        preference_modules.doomed_unless_arg_bindable($POS, $$inflowLengthsWithStartingDepths, TWO_INTEGER);
        inference_modules.inference_removal_module($REMOVAL_INFLOW_LENGTHS_WITH_STARTING_DEPTHS, $list73);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_removal_modules_math_file();
    }

    @Override
    public void initializeVariables() {
        init_removal_modules_math_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_removal_modules_math_file();
    }

    static {




























































































    }
}

/**
 * Total time: 340 ms
 */
