package com.studentsrega.studentsreg_a.repo

import com.studentsrega.studentsreg_a.model.Aluno
import org.springframework.stereotype.Repository


@Repository
class StudentsRepo {

    private var allStudents = mutableListOf<Aluno>()

    init{
        val aluno1 = Aluno(1001,"yung Buda", true)
        val aluno2 = Aluno(1002,"Aka Rasta",false)
        allStudents.add(aluno1)
        allStudents.add(aluno2)
    }



    //CRUD OPERATIONS
    //CREATE
    fun addStudent(std: Aluno):String {
        allStudents.add(std)
        return "foi adicionado o aluno ${std.nome}"
    }

    //READ
    fun retrieveAllStudents(): List<Aluno>{

        return allStudents
    }

    fun retrieveStudentById(id:Int):Aluno? {
        return allStudents.find { std ->
            std.id == id
        }
    }

    //UPDATE TPC
    //update do nome ou active ou ambos
    fun updateStudent(id:Int): List<Aluno> {
        var std:Aluno? = retrieveStudentById(id)
        std?.nome = "Andre"
        std?.active = false
        return allStudents
    }

    //DELETE

    fun deleteStudent(id:Int): List<Aluno>{
        var std:Aluno? = retrieveStudentById(id)
        allStudents.remove(std)
        return allStudents
    }

}