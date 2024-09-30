public class Temperature {
    Double[] meanDayTemp = new Double[7];
    public Temperature() {
        meanDayTemp[0] = 0.0;
        meanDayTemp[1] = 0.0;
        meanDayTemp[2] = 0.0;
        meanDayTemp[3] = 0.0;
        meanDayTemp[4] = 0.0;
        meanDayTemp[5] = 0.0;
        meanDayTemp[6] = 0.0;
    }

    boolean checkIfSpring(){
        for (Double t : meanDayTemp) {
            if (t < 0 || t > 10) {
                return false;
            }
        }
        return true;
    }

    void setMeanDayTemp(int index, Double value) {
        meanDayTemp[index] = value;
    }
}
