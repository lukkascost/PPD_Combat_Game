package Agenda.ContactsNodePackage;


/**
* Agenda/ContactsNodePackage/CustomerSeqHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Sunday, April 29, 2018 2:58:09 PM BRT
*/

abstract public class CustomerSeqHelper
{
  private static String  _id = "IDL:Agenda/ContactsNode/CustomerSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, Agenda.ContactsNodePackage.Contact[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Agenda.ContactsNodePackage.Contact[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = Agenda.ContactsNodePackage.ContactHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (Agenda.ContactsNodePackage.CustomerSeqHelper.id (), "CustomerSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Agenda.ContactsNodePackage.Contact[] read (org.omg.CORBA.portable.InputStream istream)
  {
    Agenda.ContactsNodePackage.Contact value[] = null;
    int _len0 = istream.read_long ();
    value = new Agenda.ContactsNodePackage.Contact[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = Agenda.ContactsNodePackage.ContactHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Agenda.ContactsNodePackage.Contact[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      Agenda.ContactsNodePackage.ContactHelper.write (ostream, value[_i0]);
  }

}