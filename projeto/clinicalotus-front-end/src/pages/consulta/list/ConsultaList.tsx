import React, { useContext, useEffect, useState } from 'react';
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { Consulta } from "../type";
import { FaEdit, FaTrash } from "react-icons/fa";

function ConsultaList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [consultas, setConsultas] = useState<Consulta[]>([]);
    const [pacientes, setPacientes] = useState<Map<number, string>>(new Map<number, string>());
    const [medicos, setMedicos] = useState<Map<number, string>>(new Map<number, string>());

    useEffect(() => {
        API.get('consulta')
            .then((data) => {
                if (Array.isArray(data)) {
                    setConsultas(data);
                    onLoad();
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    const onLoad = async () => {
        try {
            const pacientesData = await API.get('paciente');
            const medicosData = await API.get('funcionario');

            if (Array.isArray(pacientesData)) {
                const pacientesMap = new Map<number, string>();
                pacientesData.forEach((paciente: { id: number; nome: string }) => {
                    pacientesMap.set(paciente.id, paciente.nome);
                });
                setPacientes(pacientesMap);
            }

            if (Array.isArray(medicosData)) {
                const medicosMap = new Map<number, string>();
                medicosData.forEach((funcionario: { id: number; nome: string }) => {
                    medicosMap.set(funcionario.id, funcionario.nome);
                });
                setMedicos(medicosMap);
            }
        } catch (e) {
            console.log(e);
        }
    };

    const remove = (id: number) => {
        API.delete('consulta/' + id)
            .then((data) => {
                API.get('consulta')
                    .then((data) => {
                        if (Array.isArray(data)) setConsultas(data);
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div>
            <h1>Lista de Consultas</h1>
            <button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}>
                Nova Consulta
            </button>
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
                {consultas.map((consulta) => {
                    return (
                        <tr key={consulta.consulta_id}>
                            <td>{consulta.dataHora}</td>
                            <td>{consulta.autorizacaoPlano ? 'Sim' : 'Não'}</td>
                            <td>{pacientes.get(consulta.paciente_id)}</td>
                            <td>{medicos.get(consulta.medico_id)}</td>
                            <td>{consulta.confirmada ? 'Sim' : 'Não'}</td>
                            <td>
                                <div>
                                    <FaEdit onClick={() => navigate('edit/' + consulta.consulta_id)}></FaEdit>
                                    <FaTrash onClick={() => remove(consulta.consulta_id)}></FaTrash>
                                </div>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
}

export default ConsultaList;
