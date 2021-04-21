package com.example.contatossqlites

class CONFIGs {
    companion object {
        val EXTRA_SAVE_NEW_CONTACT= "SAVE NEW CONTACT"
        val EXTRA_UPDATE_CONTATCT = "UPDATE CONTACT"
        private var LAST_ID = 0 //initial id

        fun getNewId():Int{
            LAST_ID++
            return LAST_ID
        }
    }

}