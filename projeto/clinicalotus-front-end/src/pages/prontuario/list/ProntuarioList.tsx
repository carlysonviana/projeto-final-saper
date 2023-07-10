import React, { useContext, useEffect, useState } from 'react'
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";
import { Prontuario } from "../type";
import {FaEdit, FaFile, FaTrash} from "react-icons/fa";
import { Form, Button, Row, Col } from 'react-bootstrap';
import { BsPlus, BsSearch, BsX, BsEyeFill } from "react-icons/bs";
import styles from '../../../components/layout/baseLayout/BaseLayout.module.scss';
import {RiMailSendLine} from "react-icons/ri";
import emailjs from "@emailjs/browser";
import {AuthContext} from "../../../store/store";
import {Paciente} from "../../paciente/type";


function ProntuarioList() {
    const API = useAPI();
    const navigate = useNavigate();
    const [prontuarioPacientes, setProntuarioPacientes] = useState<Prontuario[]>([])
    const [pacientes, setPacientes] = useState<Map<number, string>>(new Map<number, string>());
    const [emails, setEmails] = useState<Map<number, string>>(new Map<number, string>());
    const [filtroPaciente, setFiltroPaciente] = useState('');
    const [paciente, setPaciente] = useState<Paciente>();
    const auth = useContext(AuthContext);

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
                pacientesData.forEach((paciente: { id: number; nome: string, email: string}) => {
                    pacientesMap.set(paciente.id, paciente.nome);
                    emails.set(paciente.id, paciente.email);
                });
                setPacientes(pacientesMap);
                console.log(pacientesMap);
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

    const sendEmail = (id: number) => {
        API.get('prontuario/'+id).then((data) => {
            const templateProntuario = {
                from_name: auth.user?.nome,
                patient_name: pacientes.get(data.paciente_id),
                diagnostico: data.diagnostico,
                receituario: data.receituario,
                email: emails.get(data.paciente_id)
            }
            if(templateProntuario.diagnostico != '' && templateProntuario.receituario){
                emailjs.send("service_8xhwmah", "template_zerhqof", templateProntuario, "-wnNrPGvVkkkC0fIr")
                    .then((response) => alert("Email enviado com sucesso!"));
            }
            else{
                alert("Campos diagnóstico e receituário não podem ser nulos!");
            }

        })
    }

    return (
        <div className={'offset-md-1 col-md-8 '}>
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
                                            <BsEyeFill title={'Visualizar Prontuário'} className={styles.spaceIcons} onClick={() => navigate('view/' + prontuarioPacientes.id)}></BsEyeFill>
                                            <FaEdit title={'Editar Prontuário'} className={styles.spaceIcons} onClick={() => navigate('edit/' + prontuarioPacientes.id)}></FaEdit>
                                            <FaTrash title={'Apagar Prontuário'} className={styles.spaceIcons} onClick={() => remove(prontuarioPacientes.id)}></FaTrash>
                                            <FaFile title={'Histórico de Consultas'} className={styles.spaceIcons} onClick={() => navigate('historic/ '+ prontuarioPacientes.paciente_id)}></FaFile>
                                            <RiMailSendLine  title={'Enviar Prontuário'} onClick={() => sendEmail(prontuarioPacientes.id)}></RiMailSendLine>
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
