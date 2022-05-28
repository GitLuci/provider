package com.studentsrega.studentsreg_a.controller


import com.studentsrega.studentsreg_a.model.Aluno
import com.studentsrega.studentsreg_a.repo.StudentsRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController {

        var studentsRepo = StudentsRepo()

    @GetMapping("/getStudents")
    fun getStudents(): List<Aluno>{
        return studentsRepo.retrieveAllStudents()

    }
    //brownse só faz pedido Get pelo URL
    @PostMapping(value = ["/createStudent"], consumes = ["application/json"])
    fun createStudent(@RequestBody std:Aluno):String{
        return studentsRepo.addStudent(std)
    }


    //banda AVA
    //TPC:
    //curl -X POST -H "content-type: application/json" -d "{"""id""":1003, """nome""":"""artista""", """active""":true}" localhost:8080/createStudent
    //1) rota e função para encontrar 1 estudante
    @GetMapping("/getStd")
    fun getStd(): Aluno? {
        return studentsRepo.retrieveStudentById(1001)
    }
    //2) rota e função para apagar 1 estudante
    //curl -X POST -H "content-type: application/json" localhost:8080/deletStd
    @PostMapping(value = ["/deletStd"], consumes = ["application/json"])
    fun delStd(): List<Aluno> {
        studentsRepo.deleteStudent(1001)
        return studentsRepo.retrieveAllStudents()
    }
    //3) rota e função para alterar estudante
    //curl -X POST -H "content-type: application/json" localhost:8080/editStd
    @PostMapping(value=["/editStd"], consumes = ["application/json"])
    fun editStd():Aluno?{
        var std = studentsRepo.retrieveStudentById(1001)
        studentsRepo.updateStudent(std!!.id)
        return std
    }

}