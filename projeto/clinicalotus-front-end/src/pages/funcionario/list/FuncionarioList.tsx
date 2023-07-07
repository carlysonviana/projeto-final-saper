import React, { useContext, useEffect, useState } from 'react';
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { Funcionario } from "../types";
import { FaEdit, FaTrash } from "react-icons/fa";
import { Form, Button, Row, Col } from 'react-bootstrap';
import {BsPlus} from "react-icons/bs";
import {AuthContext} from "../../../store/store";

function FuncionarioList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [funcionarios, setFuncionarios] = useState<Funcionario[]>([]);
    const [filtroNome, setFiltroNome] = useState('');
    const [filtroCpf, setFiltroCpf] = useState('');

    const categoria = new Map<number, string>([[1, 'ADMIN'], [2, 'MÉDICO'], [3, 'RECEPCIONISTA']]);
    const auth = useContext(AuthContext);

    useEffect(() => {
        API.get('funcionario')
            .then((data) => {
                if (Array.isArray(data)) {
                    setFuncionarios(data);
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    const remove = (id: number) => {
        API.delete('funcionario/' + id)
            .then((data) => {
                API.get('funcionario')
                    .then((data) => {
                        if (Array.isArray(data)) {
                            setFuncionarios(data);
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const filtrarFuncionarios = () => {
        const funcionariosFiltrados = funcionarios.filter((funcionario) => {
            if (filtroNome && !funcionario.nome.toLowerCase().includes(filtroNome.toLowerCase())) {
                return false;
            }
            if (filtroCpf && !funcionario.cpf.toLowerCase().includes(filtroCpf.toLowerCase())) {
                return false;
            }
            return true;
        });

        setFuncionarios(funcionariosFiltrados);
    };

    const limparFiltros = () => {
        setFiltroNome('');
        setFiltroCpf('');
        API.get('funcionario')
            .then((data) => {
                if (Array.isArray(data)) {
                    setFuncionarios(data);
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className={'offset-md-1 col-md-8'}>
            <h1>Lista de Funcionários</h1>
            <Form>
                <Row>
                    <Col>
                        <Form.Control type="text" placeholder="Filtrar por Nome" value={filtroNome} onChange={(e) => setFiltroNome(e.target.value)} />
                    </Col>
                    <Col>
                        <Form.Control type="text" placeholder="Filtrar por CPF" value={filtroCpf} onChange={(e) => setFiltroCpf(e.target.value)} />
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={filtrarFuncionarios}>
                            Filtrar
                        </Button>
                    </Col>
                    <Col>
                        <Button variant="danger" onClick={limparFiltros}>
                            Limpar
                        </Button>
                    </Col>
                    {auth.user?.categoriaFuncionario_id === 1 &&
                        <Col>
                            <Button variant="success" onClick={() => navigate('add')}>
                                <BsPlus /> Adicionar
                            </Button>
                        </Col>
                    }
                </Row>
            </Form>
            <table className={'table table-striped table-bordered table-condensed table-hover'}>
                <thead>
                <tr>
                    <th>NOME</th>
                    <th>CPF</th>
                    <th>E-MAIL</th>
                    <th>CELULAR</th>
                    <th>CATEGORIA FUNCIONÁRIO</th>
                    {auth.user?.categoriaFuncionario_id === 1 && <th>AÇÕES</th>}
                </tr>
                </thead>
                <tbody>
                {funcionarios.map((funcionario) => {
                    return (
                        <tr key={funcionario.id}>
                            <td>{funcionario.nome}</td>
                            <td>{funcionario.cpf}</td>
                            <td>{funcionario.email}</td>
                            <td>{funcionario.celular}</td>
                            <td>{categoria.get(funcionario.categoriaFuncionario_id)}</td>
                            {auth.user?.categoriaFuncionario_id === 1 && (
                                <td>
                                    <div>
                                        <FaEdit onClick={() => navigate('edit/' + funcionario.id)} />
                                        <FaTrash onClick={() => remove(funcionario.id)} />
                                    </div>
                                </td>
                            )}
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
}

export default FuncionarioList;
