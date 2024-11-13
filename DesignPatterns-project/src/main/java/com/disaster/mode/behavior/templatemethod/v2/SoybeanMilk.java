package com.disaster.mode.behavior.templatemethod.v2;

public abstract class SoybeanMilk {
    public void make() {
        materialsSelect();
        if (isAddCondiments()){
            ingredientsAdd();
        }
        soak();
        beat();
    }

    void materialsSelect() {
        System.out.println("选优良的豆子");
    }

    abstract void ingredientsAdd();

    void soak() {
        System.out.println("将所有材料进行浸泡");
    }

    void beat() {
        System.out.println("将所有材料放入豆浆机中打碎");
    }
    boolean isAddCondiments(){
        return true;
    }
}
