package com.example.gismo.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReportCard {

    private String mathGrade = "A";
    private int physicsGrade = 92;
    private double historyGrade = 95.4;
    private String chemistryGrade = "B+";
    private int germanGrade = 89;

    public String getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(String mathGrade) {
        this.mathGrade = mathGrade;
    }

    public int getPhysicsGrade() {
        return physicsGrade;
    }

    public void setPhysicsGrade(int physicsGrade) {
        this.physicsGrade = physicsGrade;
    }

    public double getHistoryGrade() {
        return historyGrade;
    }

    public void setHistoryGrade(double historyGrade) {
        this.historyGrade = historyGrade;
    }

    public String getChemistryGrade() {
        return chemistryGrade;
    }

    public void setChemistryGrade(String chemistryGrade) {
        this.chemistryGrade = chemistryGrade;
    }

    public int getGermanGrade() {
        return germanGrade;
    }

    public void setGermanGrade(int germanGrade) {
        this.germanGrade = germanGrade;
    }

    public ReportCard(String mathGrade, int physicsGrade, double historyGrade, String chemistryGrade, int germanGrade) {
        this.mathGrade = mathGrade;
        this.physicsGrade = physicsGrade;
        this.historyGrade = historyGrade;
        this.chemistryGrade = chemistryGrade;
        this.germanGrade = germanGrade;
    }

    @Override
    public String toString() {

        return "Math grade: " + String.valueOf(mathGrade) +
                ", Physics grade: " + String.valueOf(physicsGrade) +
                ", History grade: " + String.valueOf(historyGrade) +
                ", Chemistry grade: " + String.valueOf(chemistryGrade) +
                ", German grade: " + String.valueOf(germanGrade);
        
    }

}
