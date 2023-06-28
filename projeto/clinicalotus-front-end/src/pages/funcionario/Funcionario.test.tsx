import React from 'react'
import { render, screen } from '@testing-library/react'
import Funcionario from "./Funcionario";

test('renders learn react link', () => {
  render(<Funcionario />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
