package com.codechallenge.apilist.model

import java.io.Serializable

data class ItemsViewModel(val api: String, val description: String, val auth: String, val https: String, val cors: String, val link: String, val category: String) : Serializable {

}
