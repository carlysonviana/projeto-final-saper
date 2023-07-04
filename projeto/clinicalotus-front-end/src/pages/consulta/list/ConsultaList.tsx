import React, {useContext, useEffect, useState} from 'react'
import useAPI from "../../../service/api";
import {useNavigate} from "react-router-dom";
import {Consulta} from "../type";
import {FaEdit, FaTrash} from "react-icons/fa";
function ConsultaList(){
    const API = useAPI();
    const navigate = useNavigate();
    const [consultas, setConsultas] = useState<Consulta[]>([])

    useEffect(() => {
        API.get('consulta').then((data) =>{
            if(Array.isArray(data)) setConsultas(data);
        })
    }, [])

    const remove = (id: number) => {
        API.delete('consulta/' + id).then((data) =>{
            API.get('consulta').then((data) =>{
                if(Array.isArray(data)) setConsultas(data);
            })
        })
    }

    return <div>
        <h1>Lista de Consultas</h1>
        <button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}> Nova Consulta </button>
        <table className={'table table-bordered'}>
            <thead>
                <tr>
                    <th>DATA/HORA</th>
                    <th>AUTORIZACAO PLANO?</th>
                    <th>PACIENTE</th>
                    <th>MÉDICO</th>
                    <th>CONFIRMADA</th>
                    <th>AÇÕES</th>
                </tr>
            </thead>
            <tbody>
            {
                consultas.map((consulta) => {
                    return (
                        <tr key={consulta.consulta_id}>
                            <td>
                                {consulta.dataHora}
                            </td>
                            <td>
                                {consulta.autorizacaoPlano? 'Sim': 'Não'}
                            </td>
                            <td>
                                {consulta.paciente_id}
                            </td>
                            <td>
                                {consulta.medico_id}
                            </td>
                            <td>
                                {consulta.confirmada? 'Sim': 'Não'}
                            </td>
                            <td>
                                <div>
                                    <FaEdit onClick={() => navigate('edit/' + consulta.consulta_id)}></FaEdit>
                                    <FaTrash onClick={() => remove(consulta.consulta_id)}></FaTrash>
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
export default ConsultaList
