import React, {useContext, useEffect, useState} from 'react'
import useAPI from "../../../service/api";
import {useNavigate} from "react-router-dom";
import {Paciente} from "../type";
import {FaEdit, FaTrash} from "react-icons/fa";
import {Button, Col, Form, Row} from "react-bootstrap";
import {BsPlus, BsSearch, BsX} from "react-icons/bs";
function PacienteList(){
    const API = useAPI();
    const navigate = useNavigate();
    const [pacientes, setPacientes] = useState<Paciente[]>([])
    const [filtroNome, setFiltroNome] = useState('');
    const [filtroCPF, setFiltroCPF] = useState('');

    const buscarPacientes = async () => {
        const data = await API.get('paciente');
        if (Array.isArray(data)) setPacientes(data);
    };

    useEffect(() => {
        buscarPacientes();
    }, [])

    const limparFiltros = () => {
        // Limpar os valores dos campos de filtro
        setFiltroNome('');
        setFiltroCPF('');
        // Restaurar a lista original de pacientes
        buscarPacientes();
    };

    const remove = (id: number) => {
        API.delete('paciente/' + id).then((data) =>{
            API.get('paciente').then((data) =>{
                if(Array.isArray(data)) setPacientes(data);
            })
        })
    }

    const filtrarPacientes = () => {
        // Filtrar pacientes com base nos valores dos campos de filtro
        const pacientesFiltrados = pacientes.filter((paciente) => {
            // Verificar se o nome do paciente corresponde ao filtro de nome
            if (filtroNome && !paciente.nome.toLowerCase().includes(filtroNome.toLowerCase())) {
                return false;
            }
            // Verificar se o CPF do paciente corresponde ao filtro de CPF
            if (filtroCPF && !paciente.cpf.includes(filtroCPF)) {
                return false;
            }
            return true;
        });

        // Atualizar a lista de pacientes com os resultados filtrados
        setPacientes(pacientesFiltrados);
    };

    return <div className={'offset-md-1 col-md-8'}>
        <h1>Lista de Pacientes</h1>
        <Form>
            <Row>
                <Col>
                    <Form.Control type="text" placeholder="Filtrar por nome" value={filtroNome} onChange={(e) => setFiltroNome(e.target.value)} />
                </Col>
                <Col>
                    <Form.Control type="text" placeholder="Filtrar por CPF" value={filtroCPF} onChange={(e) => setFiltroCPF(e.target.value)} />
                </Col>
                <Col>
                    <Button variant="primary" onClick={filtrarPacientes}>
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
        {/*<button onClick={() => navigate('add')} className={'btn btn-sm btn-success'}> Adicionar </button>*/}
        <table className={'table table-striped table-bordered table-condensed table-hover'}>
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
