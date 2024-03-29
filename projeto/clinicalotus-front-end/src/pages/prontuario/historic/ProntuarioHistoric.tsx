import React, { useContext, useEffect, useState } from 'react';
import useAPI from "../../../service/api";
import {useNavigate, useParams} from "react-router-dom";
import { Consulta } from "../../consulta/type";
import {FaCheck, FaEdit, FaTrash} from "react-icons/fa";
import { Form, Button, Row, Col } from 'react-bootstrap';
import {BsPlus, BsSearch, BsX} from "react-icons/bs";
import {AuthContext} from "../../../store/store";

function ProntuarioHistoric() {
    const API = useAPI();
    const navigate = useNavigate();
    const [consultas, setConsultas] = useState<Consulta[]>([]);
    const [pacientes, setPacientes] = useState<Map<number, string>>(new Map<number, string>());
    const [medicos, setMedicos] = useState<Map<number, string>>(new Map<number, string>());
    const [filtroDataHora, setFiltroDataHora] = useState('');
    const [filtroAutorizacaoPlano, setFiltroAutorizacaoPlano] = useState('');
    const [filtroPaciente, setFiltroPaciente] = useState('');
    const [filtroMedico, setFiltroMedico] = useState('');
    const [filtroConfirmada, setFiltroConfirmada] = useState('');
    const auth = useContext(AuthContext);

    const { paciente_id } = useParams();

    useEffect(() => {
        API.get('consulta/busca?confirmada=true&pacienteId='+paciente_id)
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
                pacientesData.forEach((paciente: { id: number; nome: string}) => {
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

    const filtrarConsultas = () => {
        const consultasFiltradas = consultas.filter((consulta) => {
            if (filtroDataHora && !consulta.dataHora.toLowerCase().includes(filtroDataHora.toLowerCase())) {
                return false;
            }
            if (filtroAutorizacaoPlano && (consulta.autorizacaoPlano? 'Sim':'Não') !== filtroAutorizacaoPlano) {
                return false;
            }
            if (filtroMedico && !medicos.get(consulta.medico_id)?.toLowerCase().includes(filtroMedico.toLowerCase())) {
                return false;
            }
            if (filtroConfirmada && (consulta.confirmada? 'Sim': 'Não') !== filtroConfirmada) {
                return false;
            }
            return true;
        });

        setConsultas(consultasFiltradas);
    };

    const limparFiltros = () => {
        setFiltroDataHora('');
        setFiltroAutorizacaoPlano('');
        setFiltroMedico('');
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
    };

    function confirmarConsulta(consulta_id: number) {
        API.post('consulta/confirmar/'+consulta_id)
            .then((data) => {
                API.get('consulta')
                    .then((data) => {
                        if (Array.isArray(data)) setConsultas(data);
                        alert(`Consulta ${consulta_id} confirmada com sucesso!`);
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            })
            .catch((error) => {
                console.log(error);
            });
    }

    return (
        <div className={'offset-md-1 col-md-8'}>
            <h1>Histórico de Consultas</h1>
            <Form>
                <Row>
                    <Col>
                        <Form.Control type="text" placeholder="Data/Hora" value={filtroDataHora} onChange={(e) => setFiltroDataHora(e.target.value)} />
                    </Col>
                    <Col>
                        <Form.Control type="text" placeholder="Autorização" value={filtroAutorizacaoPlano} onChange={(e) => setFiltroAutorizacaoPlano(e.target.value)} />
                    </Col>
                    <Col>
                        <Form.Control type="text" placeholder="Médico" value={filtroMedico} onChange={(e) => setFiltroMedico(e.target.value)} />
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={filtrarConsultas}>
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
                            <BsPlus /> Marcar
                        </Button>
                    </Col>
                </Row>
            </Form>
            <table className={'table table-striped table-bordered table-condensed table-hover'}>
                <thead>
                <tr>
                    <th>PACIENTE:</th>
                    <td colSpan={2}>{pacientes.get(Number(paciente_id))}</td>
                </tr>
                <tr>
                    <th>DATA/HORA</th>
                    <th>AUTORIZACAO PLANO?</th>
                    <th>MÉDICO</th>
                </tr>
                </thead>
                <tbody>
                {consultas.map((consulta) => {
                    return (
                        <tr key={consulta.consulta_id}>
                            <td>{consulta.dataHora}</td>
                            <td>{consulta.autorizacaoPlano ? 'Sim' : 'Não'}</td>
                            <td>{medicos.get(consulta.medico_id)}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
}

export default ProntuarioHistoric;




