package Agenda.ContactsNodePackage;


/**
* Agenda/ContactsNodePackage/unknown_user.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* domingo, 29 de abril de 2018 17:53:08 Hor�rio da Guiana Francesa
*/

public final class unknown_user extends org.omg.CORBA.UserException
{
  public Agenda.Contact contact = null;

  public unknown_user ()
  {
    super(unknown_userHelper.id());
  } // ctor

  public unknown_user (Agenda.Contact _contact)
  {
    super(unknown_userHelper.id());
    contact = _contact;
  } // ctor


  public unknown_user (String $reason, Agenda.Contact _contact)
  {
    super(unknown_userHelper.id() + "  " + $reason);
    contact = _contact;
  } // ctor

} // class unknown_user
