package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

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
    public void sort() {
        int j=0;
        int ix,iy;
        int jx,jy;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < 255; i++) {
                j=i+1;
                ix=i/16;iy=i%16;
                jx=j/16;jy=j%16;
                if (a[ix][iy].compareTo(a[jx][jy]) > 0) {
                    swap(ix,iy,jx,jy);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}