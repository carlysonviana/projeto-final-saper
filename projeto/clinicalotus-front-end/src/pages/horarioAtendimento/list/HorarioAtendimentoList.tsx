import React, { useContext, useEffect, useState } from 'react'
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { HorarioAtendimento } from "../type";
import { FaEdit, FaTrash } from "react-icons/fa";


function HorarioAtendimentoList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [horarioAtendimentos, setHorarioAtendimentos] = useState<HorarioAtendimento[]>([])

    useEffect(() => {
        API.get('horarioAtendimento').then((data) => {
            if (Array.isArray(data)) setHorarioAtendimentos(data);
        })
    }, [])

    const remove = (id: number) => {
        API.delete('horarioAtendimento/' + id).then((data) => {
            API.get('horarioAtendimento').then((data) => {
                if (Array.isArray(data)) setHorarioAtendimentos(data);
            })
        })
    }

    return <div>
        <h1>Lista de Horarios</h1>
        <button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}> Adicionar </button>
        <table className={'table table-bordered'}>
            <thead>
                <tr>
                    <th>DIA</th>
                    <th>HORARIO INICIO</th>
                    <th>HORARIO FIM</th>
                    <th>MEDICO</th>
                </tr>
            </thead>
            <tbody>
                {
                    horarioAtendimentos.map((horarioAtendimento) => {
                        return (
                            <tr key={horarioAtendimento.horario_id}>
                                <td>
                                    {horarioAtendimento.diaDaSemana}
                                </td>
                                <td>
                                    {horarioAtendimento.horarioInicio}
                                </td>
                                <td>
                                    {horarioAtendimento.horarioFim}
                                </td>
                                <td>
                                    <div>
                                        <FaEdit onClick={() => navigate('edit/' + horarioAtendimento.horario_id)}></FaEdit>
                                        <FaTrash onClick={() => remove(horarioAtendimento.horario_id)}></FaTrash>
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
export default HorarioAtendimentoList
