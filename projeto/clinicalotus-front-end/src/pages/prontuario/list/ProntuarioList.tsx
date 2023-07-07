import React, { useContext, useEffect, useState } from 'react'
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { Prontuario } from "../type";
import { FaEdit, FaTrash } from "react-icons/fa";


function ProntuarioList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [prontuarioPacientes, setProntuarioPacientes] = useState<Prontuario[]>([])

    useEffect(() => {
        API.get('prontuario').then((data) => {
            if (Array.isArray(data)) setProntuarioPacientes(data);
        })
    }, [])

    const remove = (id: number) => {
        API.delete('prontuario/' + id).then((data) => {
            API.get('prontuario').then((data) => {
                if (Array.isArray(data)) setProntuarioPacientes(data);
            })
        })
    }

    return <div>
        <h1>Lista de Prontuarios</h1>
        <button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}> Adicionar </button>
        <table className={'table table-bordered'}>
            <thead>
                <tr>
                    <th>DIAGNOSTICO</th>
                    <th>RECEITUARIO</th>
                    <th>PACIENTE</th>
                    <th>AÇÕES</th>
                </tr>
            </thead>
            <tbody>
                {
                    prontuarioPacientes.map((prontuarioPacientes) => {
                        return (
                            <tr key={prontuarioPacientes.id}>
                                <td>
                                    {prontuarioPacientes.diagnostico}
                                </td>
                                <td>
                                    {prontuarioPacientes.receituario}
                                </td>
                                <td>
                                    {prontuarioPacientes.paciente_id}
                                </td>
                                <td>
                                    <div>
                                        <FaEdit onClick={() => navigate('edit/' + prontuarioPacientes.id)}></FaEdit>
                                        <FaTrash onClick={() => remove(prontuarioPacientes.id)}></FaTrash>
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
export default ProntuarioList
