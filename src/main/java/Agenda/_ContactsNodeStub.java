package Agenda;


/**
* Agenda/_ContactsNodeStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Sunday, April 29, 2018 1:25:48 PM BRT
*/

public class _ContactsNodeStub extends org.omg.CORBA.portable.ObjectImpl implements Agenda.ContactsNode
{

  public boolean addContact (Agenda.ContactsNodePackage.Contact contact) throws Agenda.ContactsNodePackage.user_exists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addContact", true);
                Agenda.ContactsNodePackage.ContactHelper.write ($out, contact);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Agenda/ContactsNode/user_exists:1.0"))
                    throw Agenda.ContactsNodePackage.user_existsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return addContact (contact        );
            } finally {
                _releaseReply ($in);
            }
  } // addContact

  public boolean deleteContact (Agenda.ContactsNodePackage.Contact contact) throws Agenda.ContactsNodePackage.unknown_user
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteContact", true);
                Agenda.ContactsNodePackage.ContactHelper.write ($out, contact);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Agenda/ContactsNode/unknown_user:1.0"))
                    throw Agenda.ContactsNodePackage.unknown_userHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteContact (contact        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteContact

  public boolean updateContact (Agenda.ContactsNodePackage.Contact oldContact, Agenda.ContactsNodePackage.Contact newContact) throws Agenda.ContactsNodePackage.user_exists, Agenda.ContactsNodePackage.unknown_user
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateContact", true);
                Agenda.ContactsNodePackage.ContactHelper.write ($out, oldContact);
                Agenda.ContactsNodePackage.ContactHelper.write ($out, newContact);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Agenda/ContactsNode/user_exists:1.0"))
                    throw Agenda.ContactsNodePackage.user_existsHelper.read ($in);
                else if (_id.equals ("IDL:Agenda/ContactsNode/unknown_user:1.0"))
                    throw Agenda.ContactsNodePackage.unknown_userHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return updateContact (oldContact, newContact        );
            } finally {
                _releaseReply ($in);
            }
  } // updateContact

  public void getListContacts (Agenda.ContactsNodePackage.CustomerSeqHolder contacts) throws Agenda.ContactsNodePackage.empty_list
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getListContacts", true);
                $in = _invoke ($out);
                contacts.value = Agenda.ContactsNodePackage.CustomerSeqHelper.read ($in);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Agenda/ContactsNode/empty_list:1.0"))
                    throw Agenda.ContactsNodePackage.empty_listHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                getListContacts (contacts        );
            } finally {
                _releaseReply ($in);
            }
  } // getListContacts

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Agenda/ContactsNode:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ContactsNodeStub
