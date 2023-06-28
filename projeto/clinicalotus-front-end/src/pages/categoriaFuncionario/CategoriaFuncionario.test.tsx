import React from 'react'
import { render, screen } from '@testing-library/react'
import CategoriaFuncionario from "./CategoriaFuncionario";

test('renders learn react link', () => {
  render(<CategoriaFuncionario />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
