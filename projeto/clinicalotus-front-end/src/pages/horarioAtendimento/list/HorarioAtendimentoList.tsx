import React, { useContext, useEffect, useState } from 'react'
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { HorarioAtendimento } from "../type";
import { FaEdit, FaTrash } from "react-icons/fa";
import { Form, Button, Row, Col } from 'react-bootstrap';
import { BsPlus, BsSearch, BsX } from "react-icons/bs";


function HorarioAtendimentoList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [horarioAtendimentos, setHorarioAtendimentos] = useState<HorarioAtendimento[]>([]);
    const [medicos, setMedicos] = useState<Map<number, string>>(new Map<number, string>());
    const [filtroMedico, setFiltroMedico] = useState('');

    useEffect(() => {
        API.get('horarioAtendimento').then((data) => {
            if (Array.isArray(data)) {
                setHorarioAtendimentos(data);
                onLoad();
            }
        })
    }, [])

    const onLoad = async () => {
        try {
            const medicosData = await API.get('funcionario');


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
        API.delete('horarioAtendimento/' + id).then((data) => {
            API.get('horarioAtendimento').then((data) => {
                if (Array.isArray(data)) setHorarioAtendimentos(data);
            })
        })
    }

    const filtrarHorarioAtendimento = () => {
        const horarioAtendimentosFiltrados = horarioAtendimentos.filter((horarioAtendimento) => {
            if (filtroMedico && !medicos.get(horarioAtendimento.medico_id)?.toLowerCase().includes(filtroMedico.toLowerCase())) {
                return false;
            }
            return true;
        });

        setHorarioAtendimentos(horarioAtendimentosFiltrados);
    };

    const limparFiltros = () => {
        setFiltroMedico('');
        API.get('horarioAtendimento')
            .then((data) => {
                if (Array.isArray(data)) {
                    setHorarioAtendimentos(data);
                    onLoad();
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className={'offset-md-1 col-md-8'}>
            <h1>Lista de Horários</h1>
            <Form>
                <Row>
                    <Col>
                        <Form.Control type="text" placeholder="Filtrar por Médico" value={filtroMedico} onChange={(e) => setFiltroMedico(e.target.value)} />
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={filtrarHorarioAtendimento}>
                            <BsSearch /> Filtrar
                        </Button>
                    </Col>
                    <Col>
                        <Button variant="danger" onClick={limparFiltros}>
                            <BsX /> Limpar
                        </Button>
                    </Col>
                    <Col>
                        <Button variant="success" onClick={() => navigate('add')}>
                            <BsPlus /> Adicionar
                        </Button>
                    </Col>
                </Row>
            </Form>
            <table className={'table table-striped table-bordered table-condensed table-hover'}>
                <thead>
                    <tr>
                        <th>MÉDICO</th>
                        <th>DIA</th>
                        <th>HORARIO INICIO</th>
                        <th>HORARIO FIM</th>
                        <th>AÇÕES</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        horarioAtendimentos.map((horarioAtendimento) => {
                            return (
                                <tr key={horarioAtendimento.horario_id}>
                                    <td>
                                        {medicos.get(horarioAtendimento.medico_id)}
                                    </td>
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
    );
}
export default HorarioAtendimentoList
