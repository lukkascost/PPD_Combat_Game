package Agenda.ContactsNodePackage;


/**
* Agenda/ContactsNodePackage/unknown_user.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Sunday, April 29, 2018 2:58:09 PM BRT
*/

public final class unknown_user extends org.omg.CORBA.UserException
{
  public Agenda.ContactsNodePackage.Contact contact = null;

  public unknown_user ()
  {
    super(unknown_userHelper.id());
  } // ctor

  public unknown_user (Agenda.ContactsNodePackage.Contact _contact)
  {
    super(unknown_userHelper.id());
    contact = _contact;
  } // ctor


  public unknown_user (String $reason, Agenda.ContactsNodePackage.Contact _contact)
  {
    super(unknown_userHelper.id() + "  " + $reason);
    contact = _contact;
  } // ctor

} // class unknown_user
