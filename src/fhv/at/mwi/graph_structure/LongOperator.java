package fhv.at.mwi.graph_structure;

public class LongOperator<V> implements IOperator<Long>{

    private EOperator _operator;
    private Long _value;



    public LongOperator(Long value, EOperator operator){
        setValue(value);
        setOperator(operator);
    }

    public EOperator getOperator() {
        return _operator;
    }

    public void setOperator(EOperator _operator) {
        this._operator = _operator;
    }

    public Long getValue() {
        return _value;
    }

    public void setValue(Long _value) {
        this._value = _value;
    }

    @Override
    public Long operate(Long otherValue) {
        switch (_operator){
            case ADD:
                return otherValue + _value;
            case SUBTRACT:
                return otherValue - _value;
            case DIVIDE:
                return otherValue / _value;
            case MULTIPLY:
                return otherValue * _value;
            default:
                return otherValue;
        }
    }

    @Override
    public String toString(){
        String operator = "";
        switch (_operator){
            case ADD:
                operator = "+";
                break;
            case SUBTRACT:
                operator = "-";
                break;
            case DIVIDE:
                operator = "/";
                break;
            case MULTIPLY:
                operator = "*";
                break;
        }
        return "[" + operator + " " + _value +  "]";
    }
}
