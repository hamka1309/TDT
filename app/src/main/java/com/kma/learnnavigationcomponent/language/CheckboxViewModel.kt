package com.kma.learnnavigationcomponent.language

import androidx.lifecycle.MutableLiveData

class CheckboxViewModel {
    var liveDataA = MutableLiveData<ArrayList<CheckBoxModel>>()

    fun getListCheckbox(){
        var listCheckbox = ArrayList<CheckBoxModel>()
        listCheckbox.add(CheckBoxModel(true, "All", "0"))
        listCheckbox.add(CheckBoxModel(true, "English", "1"))
        listCheckbox.add(CheckBoxModel(true, "VietNam", "2"))
        listCheckbox.add(CheckBoxModel(true, "Japan", "3"))
        listCheckbox.add(CheckBoxModel(true, "Lao", "4"))
        liveDataA.value= listCheckbox
    }
}