package no.kriben.busstopstrondheim.io;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Auth implements KvmSerializable {
    public String user;
    public String password;

    public Auth() {
    }

    public Auth(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public Object getProperty(int arg0) {

        switch (arg0) {
        case 0:
            return this.user;
        case 1:
            return this.password;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int index,
            @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
        case 0:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "user";
            break;
        case 1:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "password";
            break;
        default:
            break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
        case 0:
            user = value.toString();
            break;
        case 1:
            password = value.toString();
            break;
        default:
            break;
        }
    }
}