package Agenda.ContactsNodePackage;


/**
* Agenda/ContactsNodePackage/CustomerSeqHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Sunday, April 29, 2018 2:58:09 PM BRT
*/

public final class CustomerSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public Agenda.ContactsNodePackage.Contact value[] = null;

  public CustomerSeqHolder ()
  {
  }

  public CustomerSeqHolder (Agenda.ContactsNodePackage.Contact[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Agenda.ContactsNodePackage.CustomerSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Agenda.ContactsNodePackage.CustomerSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Agenda.ContactsNodePackage.CustomerSeqHelper.type ();
  }

}