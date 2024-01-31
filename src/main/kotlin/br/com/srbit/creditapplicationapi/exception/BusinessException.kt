package br.com.srbit.creditapplicationapi.exception

data class BusinessException(override val message: String?) : RuntimeException(message) {
}