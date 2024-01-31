package br.com.srbit.creditapplicationapi.exception

import java.time.LocalDateTime

data class ExecptionDetails(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
){
}