/*
 * Layout.java
 *
 * Copyright (C) 2003-2006 Peter Graves
 * $Id$
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */

package org.armedbear.lisp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStruct;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLNil;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLPackage;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;

abstract public class Layout extends SLispObject {
  public int getId() {
    return id;
  }

  public int getFieldCount() {
    assert (getterNames.length == slotNames.length);
    return getterNames.length;
  }

  public int getFieldNumForSymbolOffsetBy2(SubLSymbol symbol) {
    for (int i = 0, size = slotNames.length; i < size; ++i)
      if (slotNames[i] == symbol)
        return i + 2;
    Errors.error(symbol + " is not a valid field of struct: " + structName);
    return -1;
  }

  public SubLSymbol getGetterName(int i) {
    return getterNames[i];
  }

  public SubLSymbol getSetterName(int i) {
    return setterNames[i];
  }

  final public SubLSymbol getStructName() {
    if (structName == null) {
      LispObject lispClass = this.lispClass;
      if (lispClass == null)
        lispClass = getLispClass();
      // if(lispClass==STANDANRD_)
      //Thread.dumpStack();
      //structName.toString();
      if (lispClass instanceof LispClass) {
        final SubLSymbol name = (SubLSymbol) ((LispClass) lispClass).getLispClassName();
        structName = name;
        return name;
      }
    }
    return structName;
  }

  public boolean isInterned() {
    return isInterned;
  }

  public static Map<SubLSymbol, SubLStructDecl> structNameToStructDeclMap;
  static {
    structNameToStructDeclMap = new HashMap<SubLSymbol, SubLStructDecl>();
    structNameToIDMap = new HashMap<SubLSymbol, Integer>();
    SubLStructDecl.idCounter = 0;
  }

  public void setStructName(SubLSymbol newStructName) {
    if (structName != SubLNil.NIL) {
      Errors.error("Can't set a structure's name twice: " + newStructName);
    }
    structName = newStructName;
  }

  private int id;
  public boolean isInterned;
  protected SubLSymbol structName;
  protected SubLSymbol concName;
  protected SubLSymbol[] getterNames;
  // private SubLSymbol[] slotNames;
  protected SubLSymbol[] slotKeywords;
  private SubLSymbol[] setterNames;
  protected SubLSymbol printFunction;
  protected SubLSymbol hashFunction;
  protected SubLSymbol testFunction;
  public static Map<SubLSymbol, Integer> structNameToIDMap;
  public static int idCounter;

  protected boolean izTracked;

  public static int getTypeID(SubLSymbol typeName) {
    synchronized (structNameToIDMap) {
      Integer id = structNameToIDMap.get(typeName);
      if (id == null) {
        if (SubLStructDecl.idCounter >= 2048)
          Errors.error("Too many sturcuture declarations.");
        id = new Integer(SubLStructDecl.idCounter++);
        SubLStructDecl.structNameToIDMap.put(typeName, id);
      }
      return id;
    }
  }

  /**
   * TODO Describe the purpose of this method.
   */
  protected void initStructDeclFromLayout() {
    //if(true) return;
    LispObject lispClass = this.lispClass;
    if (lispClass == null)
      lispClass = getLispClass();
    // if(lispClass==STANDANRD_)
    if (lispClass instanceof LispClass) {
      final SubLSymbol name = (SubLSymbol) ((LispClass) lispClass).getLispClassName();
      structName = name;
    }
    structNameToStructDeclMap.put(structName, (SubLStructDecl) this);
    id = getTypeID(structName);
  }

  protected Layout(SubLSymbol structName, SubLSymbol[] getterNames, SubLSymbol[] setterNames, SubLSymbol[] slotKeywords, SubLSymbol printFunction, SubLSymbol hashFunction, SubLSymbol testFunction, boolean isInterned) {
    this.isInterned = false;
    this.structName = structName;
    Symbol lispSymbol = structName.toLispObject();
    // this.structName = SubLNil.NIL;
    concName = SubLNil.NIL;
    this.getterNames = getterNames;
    this.setterNames = setterNames;
    this.printFunction = printFunction;
    this.structName = structName;
    this.hashFunction = hashFunction;
    this.testFunction = testFunction;
    this.isInterned = isInterned;
    id = getTypeID(structName);
    this.slotKeywords = slotKeywords;
    slotNames = new LispObject[getterNames.length];
    SubLPackage pkg = structName.getPackage();
    for (int i = 0, size = slotNames.length; i < size; ++i)
      try {
        SubLSymbol slotKeyword = slotKeywords[i];
        String stotStr = slotKeyword.getName();
        SubLSymbol slotSymbol = SubLObjectFactory.makeSymbol(stotStr, pkg);
        slotNames[i] = (LispObject) slotSymbol;
      } catch (Exception e) {
        Errors.error(e.getMessage(), e);
      }
    sharedSlots = NIL;
    slotTable = initializeSlotTable(slotNames);

    SubLStructDecl was = structNameToStructDeclMap.get(structName);
    if (was != null) {
      if (this != was) {
        for (int i = 0; i < slotKeywords.length; i++) {
          if (was.slotKeywords[i] != slotKeywords[i])
            incompatable(was);
          if (was.getterNames[i] != getterNames[i])
            incompatable(was);
        }
      }
    }
    structNameToStructDeclMap.put(structName, (SubLStructDecl) this);

    LispClass slotClass = LispClass.findClass(lispSymbol);
    if (slotClass == null) {
      slotClass = new StructureClass(lispSymbol);
      slotClass.setClassLayout(this);
    }
    this.lispClass = slotClass;
  }

  /**
   * TODO Describe the purpose of this method.
   * @param was
   */
  private void incompatable(SubLStructDecl was) {
    // TODO Auto-generated method stub
    Error.class.toGenericString();

  }

  public LispObject isInstance(SubLObject v_object) {
    LispObject lispObject = v_object.toLispObject();
    LispObject structName2 = (LispObject) getStructName();
    return lispObject.typep(structName2);
  }

  abstract public SubLStruct newInstance();

  public LispObject lispClass;
  public final ConcurrentHashMap<LispObject, LispObject> slotTable;

  protected final LispObject[] slotNames;
  final LispObject sharedSlots;

  private boolean invalid;

  public Layout(LispObject lispClass, LispObject instanceSlots, LispObject sharedSlots) {
    this.lispClass = lispClass;

    Debug.assertTrue(instanceSlots.listp());
    int length = instanceSlots.length();
    slotNames = new LispObject[length];
    int i = 0;

    while (instanceSlots != NIL) {
      slotNames[i++] = instanceSlots.car();
      instanceSlots = instanceSlots.cdr();
    }

    Debug.assertTrue(i == length);
    this.sharedSlots = sharedSlots;
    slotTable = initializeSlotTable(slotNames);
    Debug.assertTrue(lispClass != null);
    if (lispClass == NIL)
      lispClass = null;
  }

  public Layout(LispObject lispClass, LispObject[] instanceSlotNames, LispObject sharedSlots) {
    this.lispClass = lispClass;
    this.slotNames = instanceSlotNames;
    this.sharedSlots = sharedSlots;
    slotTable = initializeSlotTable(slotNames);
    Debug.assertTrue(lispClass != null);
    if (lispClass == NIL)
      lispClass = null;
  }

  // Copy constructor.
  protected Layout(Layout oldLayout) {
    lispClass = oldLayout.getLispClass();
    slotNames = oldLayout.slotNames;
    sharedSlots = oldLayout.sharedSlots;
    slotTable = initializeSlotTable(slotNames);
    Debug.assertTrue(lispClass != null);
    if (lispClass == NIL)
      lispClass = null;
  }

  protected ConcurrentHashMap initializeSlotTable(LispObject[] slotNames) {
    ConcurrentHashMap ht = new ConcurrentHashMap(slotNames.length);
    for (int i = slotNames.length; i-- > 0;)
      ht.put(slotNames[i], Fixnum.getInstance(i));
    return ht;
  }

  @Override
  public LispObject getParts() {
    LispObject result = NIL;
    final LispObject theLispClass = getLispClass();
    result = result.push(new Cons("class", theLispClass));
    for (int i = 0; i < slotNames.length; i++) {
      result = result.push(new Cons("slot " + i, slotNames[i]));
    }
    result = result.push(new Cons("shared slots", sharedSlots));
    return result.nreverse();
  }

  public LispObject getLispClass() {
    return lispClass;
  }

  public boolean isInvalid() {
    return invalid;
  }

  public void invalidate() {
    invalid = true;
  }

  public LispObject[] getSlotNames() {
    return slotNames;
  }

  public int getLength() {
    // Required for StreamTest
    if (getterNames != null) {
      assert (getterNames.length == slotNames.length);
    }
    return slotNames.length;
  }

  public LispObject getSharedSlots() {
    return sharedSlots;
  }

  @Override
  final public String printObjectImpl() {
    final SubLSymbol named = this.getStructName();
    return unreadableString("LAYOUT " + named);
  }

  // Generates a list of slot definitions for the slot names in this layout.
  protected LispObject generateSlotDefinitions() {
    LispObject list = NIL;
    for (int i = slotNames.length; i-- > 0;)
      list = list.push(new SlotDefinition(slotNames[i], NIL));

    return list;
  }

  // ### make-layout
  private static final Primitive MAKE_LAYOUT = new Primitive("make-layout", PACKAGE_SYS, true, "class instance-slots class-slots") {
    @Override
    public LispObject execute(LispObject first, LispObject second, LispObject third)

    {
      return new SubLStructDecl(first, checkList(second), checkList(third));
    }

  };

  // ### layout-class
  private static final Primitive LAYOUT_CLASS = new Primitive("layout-class", PACKAGE_SYS, true, "layout") {
    @Override
    public LispObject execute(LispObject arg) {
      return checkLayout(arg).getLispClass();
    }
  };

  // ### layout-length
  private static final Primitive LAYOUT_LENGTH = new Primitive("layout-length", PACKAGE_SYS, true, "layout") {
    @Override
    public LispObject execute(LispObject arg) {
      return Fixnum.getInstance(checkLayout(arg).slotNames.length);
    }
  };

  public int getSlotIndex(LispObject slotName) {
    LispObject index = slotTable.get(slotName);
    if (index != null)
      return ((Fixnum) index).value;
    return -1;
  }

  public LispObject getSharedSlotLocation(LispObject slotName)

  {
    LispObject rest = sharedSlots;
    while (rest != NIL) {
      LispObject location = rest.car();
      if (location.car() == slotName)
        return location;
      rest = rest.cdr();
    }
    return null;
  }

  // ### layout-slot-index layout slot-name => index
  private static final Primitive LAYOUT_SLOT_INDEX = new Primitive("layout-slot-index", PACKAGE_SYS, true) {
    @Override
    public LispObject execute(LispObject first, LispObject second)

    {
      final LispObject slotNames[] = checkLayout(first).slotNames;
      for (int i = slotNames.length; i-- > 0;) {
        if (slotNames[i] == second)
          return Fixnum.getInstance(i);
      }
      return NIL;
    }
  };

  // ### layout-slot-location layout slot-name => location
  private static final Primitive LAYOUT_SLOT_LOCATION = new Primitive("layout-slot-location", PACKAGE_SYS, true, "layout slot-name") {
    @Override
    public LispObject execute(LispObject first, LispObject second)

    {
      final Layout layOutFirst = checkLayout(first);
      final LispObject slotNames[] = layOutFirst.slotNames;
      final int limit = slotNames.length;
      for (int i = 0; i < limit; i++) {
        if (slotNames[i] == second)
          return Fixnum.getInstance(i);
      }
      // Reaching here, it's not an instance slot.
      LispObject rest = layOutFirst.sharedSlots;
      while (rest != NIL) {
        LispObject location = rest.car();
        if (location.car() == second)
          return location;
        rest = rest.cdr();
      }
      return NIL;
    }
  };

  // ### %make-instances-obsolete class => class
  private static final Primitive _MAKE_INSTANCES_OBSOLETE = new Primitive("%make-instances-obsolete", PACKAGE_SYS, true, "class") {
    @Override
    public LispObject execute(LispObject arg) {
      final LispObject lispClass = arg;
      LispObject oldLayout;
      // Non-finalized classes might not have a valid layout, but they do
      // not have instances either so we can abort.
      if (lispClass instanceof LispClass) {
        if (!((LispClass) lispClass).isFinalized())
          return arg;
        oldLayout = ((LispClass) lispClass).getClassLayout();
      } else if (lispClass instanceof StandardObject) {
        if (((StandardObject) arg).getInstanceSlotValue(StandardClass.symFinalizedP) == NIL)
          return arg;
        oldLayout = Symbol.CLASS_LAYOUT.execute(lispClass);
      } else {
        return type_error(arg, Symbol.CLASS);
      }

      Layout newLayout = new SubLStructDecl((Layout) oldLayout);
      if (lispClass instanceof LispClass)
        ((LispClass) lispClass).setClassLayout(newLayout);
      else
        Symbol.CLASS_LAYOUT.getSymbolSetfFunction().execute(newLayout, lispClass);
      ((Layout) oldLayout).invalidate();
      return arg;
    }
  };

  abstract public boolean isTracked();

  abstract public void setTrackStructInstance(boolean trackStructInstance, int flagAt);
}
