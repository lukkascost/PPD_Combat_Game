package Agenda.ContactsNodePackage;

/**
* Agenda/ContactsNodePackage/empty_listHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* domingo, 29 de abril de 2018 16:59:18 Hor�rio da Guiana Francesa
*/

public final class empty_listHolder implements org.omg.CORBA.portable.Streamable
{
  public Agenda.ContactsNodePackage.empty_list value = null;

  public empty_listHolder ()
  {
  }

  public empty_listHolder (Agenda.ContactsNodePackage.empty_list initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Agenda.ContactsNodePackage.empty_listHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Agenda.ContactsNodePackage.empty_listHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Agenda.ContactsNodePackage.empty_listHelper.type ();
  }

}