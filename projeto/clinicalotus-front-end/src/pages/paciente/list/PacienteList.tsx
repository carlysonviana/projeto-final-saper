import React, {useContext, useEffect, useState} from 'react'
import useAPI from "../../../service/api";
import {useNavigate} from "react-router-dom";
import {Paciente} from "../type";
import {FaEdit, FaTrash} from "react-icons/fa";
function PacienteList(){
    const API = useAPI();
    const navigate = useNavigate();
    const [pacientes, setPacientes] = useState<Paciente[]>([])

    useEffect(() => {
        API.get('paciente').then((data) =>{
            if(Array.isArray(data)) setPacientes(data);
        })
    }, [])

    const remove = (id: number) => {
        API.delete('paciente/' + id).then((data) =>{
            API.get('paciente').then((data) =>{
                if(Array.isArray(data)) setPacientes(data);
            })
        })
    }

    return <div>
        <h1>Lista de Pacientes</h1>
        <button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}> Adicionar </button>
        <table className={'table table-bordered'}>
            <thead>
                <tr>
                    <th>NOME</th>
                    <th>CPF</th>
                    <th>E-MAIL</th>
                    <th>DATA DE NASCIMENTO</th>
                    <th>AÇÕES</th>
                </tr>
            </thead>
            <tbody>
            {
                pacientes.map((paciente) => {
                    return (
                        <tr key={paciente.id}>
                            <td>
                                {paciente.nome}
                            </td>
                            <td>
                                {paciente.cpf}
                            </td>
                            <td>
                                {paciente.email}
                            </td>
                            <td>
                                {paciente.dataNascimento}
                            </td>
                            <td>
                                <div>
                                    <FaEdit onClick={() => navigate('edit/' + paciente.id)}></FaEdit>
                                    <FaTrash onClick={() => remove(paciente.id)}></FaTrash>
                                </div>
                            </td>
                        </tr>
                    )
                })
            }
            </tbody>
        </table>
    </div>
}
export default PacienteList
