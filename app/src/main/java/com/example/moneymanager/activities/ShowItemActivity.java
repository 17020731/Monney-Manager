package com.example.moneymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.adapters.ShowItemsAdapter;
import com.example.moneymanager.models.Item;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShowItemActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerItems;
    private ShowItemsAdapter mAdapter;
    private ArrayList<Item>mListExpenseItem;
    private ArrayList<Item>mListIncomeItem;

    private ImageView btnBack;
    private NiceSpinner spinner;

    private LinearLayout keyboard;
    private TextView amount;
    private EditText edName;
    private ImageView icon;
    private Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    private Button minus, plus, today, dot;
    private ImageButton backspace, result;
    private static String AMOUNT = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btnBack);
        recyclerItems = findViewById(R.id.recycleItems);
        spinner = findViewById(R.id.spinner);
        keyboard = findViewById(R.id.keyboard);

        icon = findViewById(R.id.icon);
        amount = findViewById(R.id.amount);
        amount.setOnClickListener(this);
        edName = findViewById(R.id.edName);
        edName.setOnClickListener(this);

        setUpKeyboard();

        showItems(recyclerItems, getListExpense());
        List<String> dataset = new LinkedList<>(Arrays.asList("Expense", "Income"));
        spinner.attachDataSource(dataset);
        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(position == 0){
                    mListExpenseItem = getListExpense();
                    showItems(recyclerItems, mListExpenseItem);
                }else{
                    mListIncomeItem = getListIncome();
                    showItems(recyclerItems, mListIncomeItem);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                keyboard.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(keyboard.getVisibility() == View.VISIBLE){
            keyboard.setVisibility(View.INVISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }

    private ArrayList<Item> getListExpense(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("Home"));
        mListItem.add(new Item("Bills"));
        mListItem.add(new Item("Transportation"));
        mListItem.add(new Item("Home"));
        mListItem.add(new Item("Car"));
        mListItem.add(new Item("Entertainment"));
        mListItem.add(new Item("Shopping"));
        mListItem.add(new Item("Clothing"));
        mListItem.add(new Item("Insurance"));
        mListItem.add(new Item("Tax"));
        mListItem.add(new Item("Telephone"));
        mListItem.add(new Item("Cigarette"));
        mListItem.add(new Item("Health"));
        mListItem.add(new Item("Sport"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Pet"));
        mListItem.add(new Item("Add", true));
        return mListItem;
    }
    private ArrayList<Item> getListIncome(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("Food"));
        mListItem.add(new Item("Car"));
        mListItem.add(new Item("Sport"));
        mListItem.add(new Item("Health"));
        mListItem.add(new Item("Add", false));
        return mListItem;
    }
    private void showItems(RecyclerView recyclerItems, ArrayList<Item>mListItem){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setItemAnimator(new DefaultItemAnimator());
        ShowItemsAdapter adapter = new ShowItemsAdapter(ShowItemActivity.this, mListItem, icon, keyboard);
        recyclerItems.setAdapter(adapter);
    }

    /**
     * Keyboard
     */
    private void setUpKeyboard(){

        //Todo: Number press
        num0 = findViewById(R.id.num0);
        num0.setOnClickListener(this);
        num1 = findViewById(R.id.num1);
        num1.setOnClickListener(this);
        num2 = findViewById(R.id.num2);
        num2.setOnClickListener(this);
        num3 = findViewById(R.id.num3);
        num3.setOnClickListener(this);
        num4 = findViewById(R.id.num4);
        num4.setOnClickListener(this);
        num5 = findViewById(R.id.num5);
        num5.setOnClickListener(this);
        num6 = findViewById(R.id.num6);
        num6.setOnClickListener(this);
        num7 = findViewById(R.id.num7);
        num7.setOnClickListener(this);
        num8 = findViewById(R.id.num8);
        num8.setOnClickListener(this);
        num9 = findViewById(R.id.num9);
        num9.setOnClickListener(this);

        //Todo:
        today = findViewById(R.id.today);
        today.setOnClickListener(this);
        minus = findViewById(R.id.minus);
        minus.setOnClickListener(this);
        plus  = findViewById(R.id.plus);
        plus.setOnClickListener(this);
        dot = findViewById(R.id.dot);
        dot.setOnClickListener(this);
        backspace = findViewById(R.id.backspace);
        backspace.setOnClickListener(this);
        result = findViewById(R.id.result);
        result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.num0:
                AMOUNT = amount.getText().toString();
                if(AMOUNT.charAt(0) != '0'){
                    AMOUNT += "0";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num1:
                AMOUNT = amount.getText().toString();
                if(AMOUNT.length() == 1 && AMOUNT.charAt(0) == '0'){
                    AMOUNT = "1";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "1";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num2:
                AMOUNT = amount.getText().toString();
                if(AMOUNT.length() == 1 && AMOUNT.charAt(0) == '0'){
                    AMOUNT = "2";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "2";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num3:
                AMOUNT = amount.getText().toString();
                if(AMOUNT.length() == 1 && AMOUNT.charAt(0) == '0'){
                    AMOUNT = "3";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "3";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num4:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "4";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "4";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num5:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "5";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "5";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num6:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "6";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "6";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num7:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "7";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "7";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num8:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "8";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "8";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.num9:
                AMOUNT = amount.getText().toString();
                if(amount.length() == 1 && amount.getText().toString().charAt(0) == '0'){
                    AMOUNT = "9";
                    amount.setText(AMOUNT);
                }
                else{
                    AMOUNT += "9";
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.dot:
                AMOUNT = amount.getText().toString();
                AMOUNT += ".";
                amount.setText(AMOUNT);
                break;
            case R.id.plus:
                AMOUNT = amount.getText().toString();
                try {
                    if(!AMOUNT.contains("+") && !AMOUNT.contains("-")) {
                        AMOUNT += "+";
                        amount.setText(AMOUNT);
                    }
                    else if(AMOUNT.contains("+")){
                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("+"));
                        String number2 = AMOUNT.substring(AMOUNT.indexOf("+")+1);
//                        System.out.println(number1 +"" +number2);
                        Double num1 = Double.parseDouble(number1);
                        Double num2 = Double.parseDouble(number2);
                        amount.setText(String.valueOf(num1+num2)+"+");
                    }else if(AMOUNT.contains("-")){
                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("-"));
                        String number2 = AMOUNT.substring(AMOUNT.indexOf("-")+1);
                        Double num1 = Double.parseDouble(number1);
                        Double num2 = Double.parseDouble(number2);
                        amount.setText(String.valueOf(num1-num2)+"+");
                    }
                }catch (Exception e){
                    System.out.println("Number Format");
                }

                break;
            case R.id.minus:
                AMOUNT = amount.getText().toString();
                try {
                    if(!AMOUNT.contains("-") && !AMOUNT.contains("+")) {
                        AMOUNT += "-";
                        amount.setText(AMOUNT);
                    }
                    else if(AMOUNT.contains("-")){
                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("-"));
                        String number2 = AMOUNT.substring(AMOUNT.indexOf("-")+1);
                        Double num1 = Double.parseDouble(number1);
                        Double num2 = Double.parseDouble(number2);
                        amount.setText(String.valueOf(num1-num2)+"-");
                    } else if(AMOUNT.contains("+")){
                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("+"));
                        String number2 = AMOUNT.substring(AMOUNT.indexOf("+")+1);
                        Double num1 = Double.parseDouble(number1);
                        Double num2 = Double.parseDouble(number2);
                        amount.setText(String.valueOf(num1+num2)+"-");
                    }

                }catch (Exception e){
                    System.out.println("Number Format");
                }
                break;
            case R.id.today:
                AMOUNT = amount.getText().toString();

                break;
            case R.id.backspace:
                AMOUNT = amount.getText().toString();
                if(AMOUNT.length() == 1){
                amount.setText("0");
                }
                else if(AMOUNT.length()!=0) {
                    AMOUNT = AMOUNT.substring(0, AMOUNT.length() - 1);
                    amount.setText(AMOUNT);
                }
                break;
            case R.id.result:
//                try {
//                    if(AMOUNT.contains("+")){
//                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("+"));
//                        String number2 = AMOUNT.substring(AMOUNT.indexOf("+")+1);
//                        Double num1 = Double.parseDouble(number1);
//                        Double num2 = Double.parseDouble(number2);
//                        amount.setText(String.valueOf(num1+num2));
//                    }
//                    else if(AMOUNT.contains("-")){
//                        String number1 = AMOUNT.substring(0, AMOUNT.indexOf("-"));
//                        String number2 = AMOUNT.substring(AMOUNT.indexOf("-")+1);
//                        Double num1 = Double.parseDouble(number1);
//                        Double num2 = Double.parseDouble(number2);
//                        amount.setText(String.valueOf(num1-num2));
//                    }
//                }catch (NumberFormatException e){
//                    System.out.println("Number Format");
//                }

                Intent intent = new Intent(ShowItemActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
