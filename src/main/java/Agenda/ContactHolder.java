package Agenda;

/**
* Agenda/ContactHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Sunday, April 29, 2018 4:04:17 PM BRT
*/

public final class ContactHolder implements org.omg.CORBA.portable.Streamable
{
  public Agenda.Contact value = null;

  public ContactHolder ()
  {
  }

  public ContactHolder (Agenda.Contact initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Agenda.ContactHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Agenda.ContactHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Agenda.ContactHelper.type ();
  }

}
