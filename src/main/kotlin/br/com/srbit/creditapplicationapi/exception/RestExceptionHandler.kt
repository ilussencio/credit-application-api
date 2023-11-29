package br.com.srbit.creditapplicationapi.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExecptionDetails>{
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach{
            erro: ObjectError ->
            val fildName: String = (erro as FieldError).field
            val messageError: String? = erro.defaultMessage
            erros[fildName] = messageError
        }
        return ResponseEntity(
            ExecptionDetails(
            title = "Bad Request! Consult the documentation",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.toString(),
            details = erros
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerDataException(ex: DataAccessException): ResponseEntity<ExecptionDetails>{
        return ResponseEntity(
            ExecptionDetails(
                title = "Bad Request! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BusinessException::class)
    fun handlerBusinessException(ex: BusinessException): ResponseEntity<ExecptionDetails>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExecptionDetails(
                    title = "Bad Request! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }

    @ExceptionHandler(IllegalAccessException::class)
    fun handlerIllegalAccessException(ex: IllegalAccessException): ResponseEntity<ExecptionDetails>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExecptionDetails(
                    title = "Bad Request! Consult the documentation",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }
}