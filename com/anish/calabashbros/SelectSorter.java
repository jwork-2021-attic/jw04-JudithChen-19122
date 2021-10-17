package com.anish.calabashbros;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T>{
    private T[][] a;

    @Override
    public void load(T[][] cal) {

        this.a= cal;
    }

    private void swap(int ix, int iy,  int jx, int jy) {
        T temp;
        temp = a[ix][iy];
        a[ix][iy] = a[jx][jy];
        a[jx][jy] = temp;
        plan += "" + a[ix][iy] + "<->" + a[jx][jy] + "\n";
    }

    private String plan = "";


    @Override
    public String getPlan() {
        return this.plan;
    }
    
    @Override
    public void sort() {
        int ax=0,ay=0;
        int bx=0,by=0;          
        for(int i=0;i<255;i++){
            int minIndex = i;
            ax = minIndex/16;  ay = minIndex%16;
            for(int j = i + 1; j < 256; j++){ 
                bx = j/16; by = j%16;
                if(a[ax][ay].compareTo(a[bx][by]) > 0){
                    minIndex = j;
                    ax=bx; ay=by;
                }
            }
            swap(i/16,i%16, ax,ay); 
        }
    } 
}
