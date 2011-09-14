/**
 * Copyright (C) 2011 Inqwell Ltd
 *
 * You may distribute under the terms of the Artistic License, as specified in
 * the README file.
 */

package com.inqwell.any.client.dock;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import bibliothek.gui.dock.common.action.CAction;
import bibliothek.gui.dock.common.intern.action.CDecorateableAction;

import com.inqwell.any.AbstractComposite;
import com.inqwell.any.AbstractValue;
import com.inqwell.any.Any;
import com.inqwell.any.AnyBoolean;
import com.inqwell.any.AnyException;
import com.inqwell.any.AnyRuntimeException;
import com.inqwell.any.Array;
import com.inqwell.any.BooleanI;
import com.inqwell.any.Event;
import com.inqwell.any.IntI;
import com.inqwell.any.Set;
import com.inqwell.any.client.AnyComponent;
import com.inqwell.any.client.AnyView;
import com.inqwell.any.client.RenderInfo;

public class AnyCAction extends AnyView
{
  private CAction a_;
  
  private static Set properties__;
  private static Any mnemonic__ = AbstractValue.flyweightString("mnemonic");
  
  static
  {
    properties__ = AbstractComposite.set();
    properties__.add(AnyComponent.accelerator__);
    properties__.add(AnyComponent.visible__);
    properties__.add(mnemonic__);
    properties__.add(AnyComponent.enabled__);
  }

  @Override
  protected void componentProcessEvent(Event e) throws AnyException
  {
    // TODO Auto-generated method stub
  }

  @Override
  protected Object getAttachee(Any eventType)
  {
    return getObject();
  }

  @Override
  protected Object getPropertyOwner(Any property)
  {
    if (properties__.contains(property))
      return this;

    return a_;
  }

  @Override
  protected RenderInfo getRenderInfo()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getAddIn()
  {
    return getObject();
  }

  @Override
  public Object getAddee()
  {
    return getObject();
  }

  @Override
  public JComponent getBorderee()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLabel()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void requestFocus()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void setEnabled(Any enabled)
  {
    if (getCAction() instanceof CDecorateableAction)
    {
      b__.copyFrom(enabled);
      CDecorateableAction a = (CDecorateableAction)getCAction();
      a.setEnabled(b__.getValue());
    }
  }
  
  @Override
  public void setRenderInfo(RenderInfo r)
  {
    // TODO Auto-generated method stub

  }

  public void setAccelerator(Array accelerator)
  {
    if (getCAction() instanceof CDecorateableAction)
    {
      IntI       kc      = (IntI)accelerator.get(0);
      IntI       mod     = (IntI)accelerator.get(1);
      BooleanI   release = (BooleanI)accelerator.get(2); 
      
      CDecorateableAction a = (CDecorateableAction)getCAction();
      KeyStroke k = KeyStroke.getKeyStroke(kc.getValue(),
                                           mod.getValue(),
                                           release.getValue());
      a.setAccelerator(k);
    }
    
  }
  
  public void setMenmonic(Any mnemonic)
  {
    // No op for compatibility with menu items. No
    // mnemonic for dock actions.
  }
  
  public void setVisible(Any visible)
  {
    // No op for compatibility with menu items. No
    // visibility for dock actions.
  }
  
  public Any getVisible()
  {
    return new AnyBoolean(true);
  }
  
  @Override
  public Object getObject()
  {
    return a_;
  }
  
  CAction getCAction()
  {
    return a_;
  }

  @Override
  public void setObject(Object o)
  {
    if (!(o instanceof CAction))
      throw new AnyRuntimeException("Not a CAction");
    
    a_ = (CAction)o;
    
    setupEventSet(o);
  }

  // A marker interface that also brings together the methods
  // already present in dockingframes' implementing classes.
  // Makes layout of action hierarchies easier (see Inq.jj)
  public static interface ActionContainer
  {
    public void add(CAction action);
    public void insert(int index, CAction action);
    public void addSeparator();
    public void insertSeparator(int index);
    public void remove( int index );
    public void remove(CAction action);
  }
}
