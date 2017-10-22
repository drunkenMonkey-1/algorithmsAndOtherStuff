package fhv.at.mwi.quality_control;

import fhv.at.mwi.general.SimpleMap;

public class TestMain {
    public static void main(String[] args){
        SimpleMap[][] sample = new SimpleMap[7][];
        sample[0] = SimpleMap.initWithString("+-----------------+");
        sample[1] = SimpleMap.initWithString("|    **    *      |");
        sample[2] = SimpleMap.initWithString("|   *  *  *   *   |");
        sample[3] = SimpleMap.initWithString("|   ***      * *  |");
        sample[4] = SimpleMap.initWithString("|        **   *   |");
        sample[5] = SimpleMap.initWithString("|  *     **       |");
        sample[6] = SimpleMap.initWithString("+-----------------+");

        QualityControl qc = new QualityControl(sample);
        System.out.println(qc.get_matches());
    }
}
