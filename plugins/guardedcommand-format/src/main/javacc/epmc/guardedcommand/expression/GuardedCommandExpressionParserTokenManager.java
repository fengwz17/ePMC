/* Generated By:JavaCC: Do not edit this line. GuardedCommandExpressionParserTokenManager.java */
package epmc.guardedcommand.expression;
import epmc.error.UtilError;
import epmc.guardedcommand.error.ProblemsGuardedCommand;
import epmc.error.Positional;
import static epmc.error.UtilError.ensure;
import epmc.error.EPMCException;
import epmc.value.Operator;
import epmc.value.Value;
import epmc.value.ContextValue;
import epmc.value.OperatorAnd;
import epmc.value.OperatorNot;
import epmc.value.OperatorAddInverse;
import epmc.value.OperatorImplies;
import epmc.value.OperatorIff;
import epmc.value.OperatorOr;
import epmc.value.OperatorIte;
import epmc.value.OperatorEq;
import epmc.value.OperatorNe;
import epmc.value.OperatorAdd;
import epmc.value.OperatorSubtract;
import epmc.value.OperatorLe;
import epmc.value.OperatorLt;
import epmc.value.OperatorGe;
import epmc.value.OperatorGt;
import epmc.value.OperatorMultiply;
import epmc.value.OperatorDivide;
import epmc.value.OperatorPow;
import epmc.value.OperatorMax;
import epmc.value.OperatorMin;
import epmc.value.OperatorCeil;
import epmc.value.OperatorFloor;
import epmc.value.OperatorLog;
import epmc.value.OperatorMod;
import epmc.value.ValueInteger;
import epmc.value.TypeInteger;
import epmc.value.TypeReal;
import epmc.value.UtilValue;
import epmc.value.ValueAlgebra;
import epmc.expression.*;
import epmc.expression.standard.ExpressionIdentifierStandard;
import epmc.expression.standard.ExpressionFilter;
import epmc.expression.standard.ExpressionMultiObjective;
import epmc.expression.standard.ExpressionCoalition;
import epmc.expression.standard.ExpressionTemporal;
import epmc.expression.standard.ExpressionReward;
import epmc.expression.standard.ExpressionQuantifier;
import epmc.expression.standard.ExpressionSteadyState;
import epmc.expression.standard.ExpressionLiteral;
import epmc.expression.standard.ExpressionOperator;
import epmc.expression.standard.TemporalType;
import epmc.expression.standard.FilterType;
import epmc.expression.standard.TimeBound;
import epmc.expression.standard.RewardType;
import epmc.expression.standard.CmpType;
import epmc.expression.standard.DirType;
import java.util.ArrayList;
import java.util.List;

/** Token Manager. */
public class GuardedCommandExpressionParserTokenManager implements GuardedCommandExpressionParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x4000000000000L) != 0L)
            return 12;
         if ((active0 & 0x479bfef3edc0L) != 0L || (active1 & 0x32000L) != 0L)
         {
            jjmatchedKind = 82;
            return 1;
         }
         if ((active0 & 0x3864010c1220L) != 0L)
            return 1;
         return -1;
      case 1:
         if ((active0 & 0x47fbfef3edc0L) != 0L || (active1 & 0x32000L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 82;
               jjmatchedPos = 1;
            }
            return 1;
         }
         return -1;
      case 2:
         if ((active0 & 0x47f904f3edc0L) != 0L || (active1 & 0x30000L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 82;
               jjmatchedPos = 2;
            }
            return 1;
         }
         if ((active0 & 0x2fa000000L) != 0L || (active1 & 0x2000L) != 0L)
            return 1;
         return -1;
      case 3:
         if ((active0 & 0x431984e1ed00L) != 0L || (active1 & 0x30000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 3;
            return 1;
         }
         if ((active0 & 0x4e0001200c0L) != 0L)
            return 1;
         return -1;
      case 4:
         if ((active0 & 0x31980e0cc00L) != 0L || (active1 & 0x30000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 4;
            return 1;
         }
         if ((active0 & 0x400004012100L) != 0L)
            return 1;
         return -1;
      case 5:
         if ((active0 & 0x10080008800L) != 0L || (active1 & 0x10000L) != 0L)
            return 1;
         if ((active0 & 0x21900e04400L) != 0L || (active1 & 0x20000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 5;
            return 1;
         }
         return -1;
      case 6:
         if ((active0 & 0x800204000L) != 0L)
            return 1;
         if ((active0 & 0x21100c00400L) != 0L || (active1 & 0x20000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 6;
            return 1;
         }
         return -1;
      case 7:
         if ((active0 & 0x400L) != 0L)
            return 1;
         if ((active0 & 0x21100c00000L) != 0L || (active1 & 0x20000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 7;
            return 1;
         }
         return -1;
      case 8:
         if ((active0 & 0x20100400000L) != 0L || (active1 & 0x20000L) != 0L)
            return 1;
         if ((active0 & 0x1000800000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 8;
            return 1;
         }
         return -1;
      case 9:
         if ((active0 & 0x1000000000L) != 0L)
            return 1;
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 9;
            return 1;
         }
         return -1;
      case 10:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 10;
            return 1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 64;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x40L);
      case 34:
         return jjStopAtPos(0, 75);
      case 38:
         return jjStopAtPos(0, 62);
      case 39:
         return jjStopAtPos(0, 68);
      case 40:
         return jjStopAtPos(0, 54);
      case 41:
         return jjStopAtPos(0, 55);
      case 42:
         return jjStopAtPos(0, 49);
      case 43:
         return jjStopAtPos(0, 47);
      case 44:
         return jjStopAtPos(0, 61);
      case 45:
         jjmatchedKind = 48;
         return jjMoveStringLiteralDfa1_0(0x8000000000000L, 0x0L);
      case 46:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1000L);
      case 47:
         return jjStartNfaWithStates_0(0, 50, 12);
      case 58:
         return jjStopAtPos(0, 60);
      case 59:
         return jjStopAtPos(0, 59);
      case 60:
         jjmatchedKind = 72;
         return jjMoveStringLiteralDfa1_0(0x400000000000000L, 0x4084L);
      case 61:
         jjmatchedKind = 69;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2L);
      case 62:
         jjmatchedKind = 73;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x8400L);
      case 63:
         return jjStopAtPos(0, 67);
      case 65:
         return jjStartNfaWithStates_0(0, 5, 1);
      case 67:
         return jjStartNfaWithStates_0(0, 9, 1);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x400L, 0x0L);
      case 69:
         return jjStartNfaWithStates_0(0, 12, 1);
      case 70:
         return jjStartNfaWithStates_0(0, 18, 1);
      case 71:
         return jjStartNfaWithStates_0(0, 19, 1);
      case 73:
         return jjStartNfaWithStates_0(0, 24, 1);
      case 82:
         jjmatchedKind = 34;
         return jjMoveStringLiteralDfa1_0(0x6000000000L, 0x0L);
      case 85:
         return jjStartNfaWithStates_0(0, 43, 1);
      case 87:
         return jjStartNfaWithStates_0(0, 44, 1);
      case 88:
         return jjStartNfaWithStates_0(0, 45, 1);
      case 91:
         return jjStopAtPos(0, 52);
      case 93:
         return jjStopAtPos(0, 53);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x40L, 0x0L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x180L, 0x0L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x800L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x21100a00000L, 0x20000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x3e000L, 0x0L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x400000000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x2500000L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0xc000000L, 0x0L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0xf0000000L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x200000000L, 0x10000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x800000000L, 0x0L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x18000000000L, 0x2000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x40000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 56);
      case 124:
         return jjStopAtPos(0, 63);
      case 125:
         return jjStopAtPos(0, 57);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 45:
         if ((active0 & 0x400000000000000L) != 0L)
            return jjStopAtPos(1, 58);
         break;
      case 46:
         if ((active1 & 0x1000L) != 0L)
            return jjStopAtPos(1, 76);
         break;
      case 60:
         if ((active1 & 0x4000L) != 0L)
            return jjStopAtPos(1, 78);
         break;
      case 61:
         if ((active1 & 0x40L) != 0L)
            return jjStopAtPos(1, 70);
         else if ((active1 & 0x80L) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 1;
         }
         else if ((active1 & 0x400L) != 0L)
            return jjStopAtPos(1, 74);
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x4L);
      case 62:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 51);
         else if ((active1 & 0x2L) != 0L)
            return jjStopAtPos(1, 65);
         else if ((active1 & 0x8000L) != 0L)
            return jjStopAtPos(1, 79);
         break;
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x14002000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000080L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x400020008000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L, active1, 0x10000L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000000L, active1, 0x2000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x21102f00000L, active1, 0x20000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x2c8004940L, active1, 0L);
      case 113:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 62:
         if ((active1 & 0x4L) != 0L)
            return jjStopAtPos(2, 66);
         break;
      case 83:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000L, active1, 0x10000L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L, active1, 0L);
      case 100:
         if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 30;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x21180a00000L, active1, 0x20000L);
      case 103:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(2, 27, 1);
         else if ((active1 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(2, 77, 1);
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000100080L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0xa000L, active1, 0L);
      case 110:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(2, 29, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x20100L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x10040L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000004000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000L, active1, 0L);
      case 116:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(2, 25, 1);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000800L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000400000L, active1, 0L);
      case 119:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(2, 33, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L, active1, 0L);
      case 120:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(2, 28, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa4_0(active0, 0x400L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x800400000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x800L, active1, 0L);
      case 99:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(3, 17, 1);
         break;
      case 101:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 42, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x400004000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0xa00000L, active1, 0L);
      case 108:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(3, 6, 1);
         else if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(3, 7, 1);
         break;
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x100004000L, active1, 0L);
      case 110:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 38, 1);
         break;
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L, active1, 0L);
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 0x20000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000002100L, active1, 0L);
      case 116:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(3, 20, 1);
         else if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x10000008000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000L, active1, 0L);
      case 120:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 37, 1);
         break;
      case 121:
         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, active1);
      return 4;
   }
   switch(curChar)
   {
      case 79:
         return jjMoveStringLiteralDfa5_0(active0, 0x400L, active1, 0L);
      case 101:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(4, 13, 1);
         return jjMoveStringLiteralDfa5_0(active0, 0x11000008000L, active1, 0x10000L);
      case 108:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(4, 26, 1);
         return jjMoveStringLiteralDfa5_0(active0, 0x80000800L, active1, 0x20000L);
      case 110:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 46, 1);
         return jjMoveStringLiteralDfa5_0(active0, 0xa00000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x100000000L, active1, 0L);
      case 114:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 1);
         return jjMoveStringLiteralDfa5_0(active0, 0x800400000L, active1, 0L);
      case 116:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(4, 8, 1);
         break;
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, active1);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(3, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, active1);
      return 5;
   }
   switch(curChar)
   {
      case 85:
         return jjMoveStringLiteralDfa6_0(active0, 0x400L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 0x20000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x900000000L, active1, 0L);
      case 101:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(5, 11, 1);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(5, 31, 1);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x600000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000L, active1, 0L);
      case 109:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 40, 1);
         break;
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(5, 15, 1);
         else if ((active1 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(5, 80, 1);
         break;
      case 115:
         return jjMoveStringLiteralDfa6_0(active0, 0x20000000000L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L, active1, 0L);
      case 119:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, active1);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(4, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, active1);
      return 6;
   }
   switch(curChar)
   {
      case 78:
         return jjMoveStringLiteralDfa7_0(active0, 0x400L, active1, 0L);
      case 97:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(6, 14, 1);
         return jjMoveStringLiteralDfa7_0(active0, 0x1000c00000L, active1, 0L);
      case 115:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(6, 35, 1);
         break;
      case 116:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(6, 21, 1);
         return jjMoveStringLiteralDfa7_0(active0, 0x20000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x100000000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa7_0(active0, 0L, active1, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0, active1);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(5, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, active1);
      return 7;
   }
   switch(curChar)
   {
      case 84:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(7, 10, 1);
         break;
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x20000000000L, active1, 0x20000L);
      case 108:
         return jjMoveStringLiteralDfa8_0(active0, 0x100000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x400000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000800000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0, active1);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(6, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, active1);
      return 8;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa9_0(active0, 0x1000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(8, 32, 1);
         break;
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x800000L, active1, 0L);
      case 109:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 41, 1);
         break;
      case 114:
         if ((active1 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(8, 81, 1);
         break;
      case 116:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(8, 22, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0, active1);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(7, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa10_0(active0, 0x800000L);
      case 115:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 36, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, 0L);
      return 10;
   }
   switch(curChar)
   {
      case 110:
         return jjMoveStringLiteralDfa11_0(active0, 0x800000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, 0L);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0, 0L);
      return 11;
   }
   switch(curChar)
   {
      case 116:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(11, 23, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 15;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(6, 11);
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 12;
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 83)
                        kind = 83;
                     jjCheckNAdd(3);
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 83)
                        kind = 83;
                  }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 82)
                     kind = 82;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 83)
                     kind = 83;
                  jjCheckNAdd(3);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 83)
                     kind = 83;
                  jjCheckNAdd(3);
                  break;
               case 4:
                  if (curChar == 48 && kind > 83)
                     kind = 83;
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(6, 11);
                  break;
               case 6:
                  if (curChar == 46)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 84)
                     kind = 84;
                  jjCheckNAddTwoStates(7, 8);
                  break;
               case 9:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 84)
                     kind = 84;
                  jjCheckNAdd(10);
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 84)
                     kind = 84;
                  jjCheckNAddStates(0, 3);
                  break;
               case 12:
                  if (curChar != 47)
                     break;
                  if (kind > 85)
                     kind = 85;
                  jjCheckNAdd(13);
                  break;
               case 13:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 85)
                     kind = 85;
                  jjCheckNAdd(13);
                  break;
               case 14:
                  if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 82)
                     kind = 82;
                  jjCheckNAdd(1);
                  break;
               case 8:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(4, 5);
                  break;
               case 13:
                  if (kind > 85)
                     kind = 85;
                  jjstateSet[jjnewStateCnt++] = 13;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 13:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 85)
                     kind = 85;
                  jjstateSet[jjnewStateCnt++] = 13;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 15 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   6, 7, 8, 11, 9, 10, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\101", "\142\157\157\154", "\143\145\151\154", 
"\143\157\156\163\164", "\103", "\104\111\123\103\117\125\116\124", "\144\157\165\142\154\145", 
"\105", "\146\141\154\163\145", "\146\157\162\155\165\154\141", 
"\146\151\154\164\145\162", "\146\154\157\157\162", "\146\165\156\143", "\106", "\107", 
"\151\156\151\164", "\145\156\144\151\156\151\164", "\151\156\166\141\162\151\141\156\164", 
"\145\156\144\151\156\166\141\162\151\141\156\164", "\111", "\151\156\164", "\154\141\142\145\154", "\154\157\147", 
"\155\141\170", "\155\151\156", "\155\157\144", "\155\157\144\165\154\145", 
"\145\156\144\155\157\144\165\154\145", "\160\157\167", "\122", "\162\145\167\141\162\144\163", 
"\145\156\144\162\145\167\141\162\144\163", "\122\155\141\170", "\122\155\151\156", "\163\161\162\164", 
"\163\171\163\164\145\155", "\145\156\144\163\171\163\164\145\155", "\164\162\165\145", "\125", "\127", 
"\130", "\147\151\166\145\156", "\53", "\55", "\52", "\57", "\55\76", "\133", "\135", 
"\50", "\51", "\173", "\175", "\74\55", "\73", "\72", "\54", "\46", "\174", "\41", 
"\75\76", "\74\75\76", "\77", "\47", "\75", "\41\75", "\74\75", "\74", "\76", "\76\75", 
"\42", "\56\56", "\163\155\147", "\74\74", "\76\76", "\160\154\141\171\145\162", 
"\145\156\144\160\154\141\171\145\162", null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffffffffffffe1L, 0x5fffffL, 
};
static final long[] jjtoSkip = {
   0x1eL, 0x200000L, 
};
static final long[] jjtoSpecial = {
   0x0L, 0x200000L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[15];
private final int[] jjstateSet = new int[30];
protected char curChar;
/** Constructor. */
public GuardedCommandExpressionParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public GuardedCommandExpressionParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 15; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedPos == 0 && jjmatchedKind > 86)
   {
      jjmatchedKind = 86;
   }
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
