package fhv.at.mwi.general;

public class SimpleMap {
    private char _value;

    private boolean _used;

    public SimpleMap(char value, boolean used){
        _value = value;
        _used = used;
    }

    public static SimpleMap[] initWithString(String s){
        SimpleMap[] result = new SimpleMap[s.length()];
        for(int i = 0; i < s.length(); i++){
            result[i] = new SimpleMap(s.charAt(i), false);
        }
        return result;
    }

    public char getValue() {
        return _value;
    }

    public boolean isUsed() {
        return _used;
    }

    public void set_used(boolean _used) {
        this._used = _used;
    }
}
