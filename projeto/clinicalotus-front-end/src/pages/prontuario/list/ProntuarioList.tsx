import React, { useContext, useEffect, useState } from 'react'
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { Prontuario } from "../type";
import { FaEdit, FaTrash } from "react-icons/fa";
import { Form, Button, Row, Col } from 'react-bootstrap';
import { BsPlus, BsSearch, BsX, BsEyeFill } from "react-icons/bs";


function ProntuarioList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [prontuarioPacientes, setProntuarioPacientes] = useState<Prontuario[]>([])
    const [pacientes, setPacientes] = useState<Map<number, string>>(new Map<number, string>());
    const [filtroPaciente, setFiltroPaciente] = useState('');

    useEffect(() => {
        API.get('prontuario').then((data) => {
            if (Array.isArray(data)) {
                setProntuarioPacientes(data);
                onLoad();
            }
        })
    }, [])

    const onLoad = async () => {
        try {
            const pacientesData = await API.get('paciente');

            if (Array.isArray(pacientesData)) {
                const pacientesMap = new Map<number, string>();
                pacientesData.forEach((paciente: { id: number; nome: string }) => {
                    pacientesMap.set(paciente.id, paciente.nome);
                });
                setPacientes(pacientesMap);
            }


        } catch (e) {
            console.log(e);
        }
    };

    const remove = (id: number) => {
        API.delete('prontuario/' + id).then((data) => {
            API.get('prontuario').then((data) => {
                if (Array.isArray(data)) setProntuarioPacientes(data);
            })
        })
    }

    const filtrarProntuarios = () => {
        const prontuariosFiltrados = prontuarioPacientes.filter((prontuario) => {

            if (filtroPaciente && !pacientes.get(prontuario.paciente_id)?.toLowerCase().includes(filtroPaciente.toLowerCase())) {
                return false;
            }
            return true;
        });

        setProntuarioPacientes(prontuariosFiltrados);
    };

    const limparFiltros = () => {
        setFiltroPaciente('');
        API.get('prontuario')
            .then((data) => {
                if (Array.isArray(data)) {
                    setProntuarioPacientes(data);
                    onLoad();
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };


    return (
        <div className={'offset-md-1 col-md-8'}>
            <h1>Lista de Prontuarios</h1>
            <Form>
                <Row>
                    <Col>
                        <Form.Control type="text" placeholder="Filtrar por Paciente" value={filtroPaciente} onChange={(e) => setFiltroPaciente(e.target.value)} />
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={filtrarProntuarios}>
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
                                        {pacientes.get(prontuarioPacientes.paciente_id)}
                                    </td>
                                    <td>
                                        <div>
                                            <BsEyeFill onClick={() => navigate('view/' + prontuarioPacientes.id)}></BsEyeFill>
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
    );
}
export default ProntuarioList
