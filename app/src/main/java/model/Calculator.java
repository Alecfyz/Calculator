package model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.calculator.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static model.Operations.ADD;
import static model.Operations.DIV;
import static model.Operations.MULT;
import static model.Operations.SUB;
import static model.Operations.valueOf;

public class Calculator implements IntfcCalculator, Parcelable {
    private float arg1;
    private float arg2;
    private Operations operation;
    private float result;
    private static Resources src;

    private String CurString = "";


    public Calculator() {
    }

    public Calculator(Parcel in) {
        arg1 = in.readFloat();
        arg2 = in.readFloat();
        result = in.readFloat();
//        operation = in.readTypedObject();
        CurString = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public void readkey(String key) {
        switch (key) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                addToCurString(key);
                break;
            case "+":
                setOp(ADD);
                addToCurString(key);
                break;

            case "-":
                setOp(SUB);
                addToCurString(key);
                break;

            case "*":
                setOp(MULT);
                addToCurString(key);
                break;

            case "/":
                setOp(DIV);
                addToCurString(key);
                break;

            case "=":
                prepareToBinaryOperation();
                break;
        }

    }

    private void addToCurString(String key) {
        this.CurString = this.CurString.concat(key);
    }

    private void clearCurString() {
        this.CurString = "";
//        CurString = "";
    }

    public String getCurString() {
        return this.CurString;
    }

    private void setOp(Operations oper) {
//        clearCurString();
        operation = oper;
        convertToArg("arg1");
    }

    private void convertToArg(String argstr){
        float arg;
        if(!CurString.equals("")){
            arg = Float.parseFloat(CurString);
        } else {
            arg = 0f;
        }
        if (argstr.equals("arg1")) this.arg1 = arg; else this.arg2 = arg;
    }

    private void prepareToBinaryOperation() {
        convertToArg("arg2");
        // проверяем наличчие обоих аргументов и текущей операции. Если все ок, выполняем бинарную операцию
        doBinaryOperation(arg1, arg2, operation);
    }


    public static Resources getResourses() {
        return src;
    }



    public void doBinaryOperation(float arg1, float arg2, Operations op) {
        clear(arg1, arg2);

        Context context;
//        String message = context.getString(R.string.asd);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        switch (op) {
            case ADD:
                doAddition();
                break;
            case DIV:
                doDivision(this.arg1, this.arg2);
                break;
            case SUB:
                doSubtraction(this.arg1, this.arg2);
                break;
            case MULT:
                doMultiplication(this.arg1, this.arg2);
                break;
            default:
                throw new IllegalStateException(getResourses().getString(R.string.stext) + op);
        }
    }

    private void doAddition() {
        this.result = this.arg1 + this.arg2;
    }

    private void doDivision(float arg1, float arg2) {
        this.result = this.arg1 / this.arg2;
    }

    private void doSubtraction(float arg1, float arg2) {
        this.result = this.arg1 - this.arg2;
    }

    private void doMultiplication(float arg1, float arg2) {
        this.result = this.arg1 * this.arg2;
    }

    private void clear(float arg1, float arg2) {
        // do clear values and put it
        this.arg1 = (float) arg1;
        this.arg2 = (float) arg2;
    }

    public String getResult() {
        this.CurString = String.valueOf(this.result);
        return String.valueOf(this.result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(arg1);
        dest.writeFloat(arg2);
        dest.writeFloat(result);
        dest.writeString(CurString);
    }


    // float arg1, float arg2, String op
}
