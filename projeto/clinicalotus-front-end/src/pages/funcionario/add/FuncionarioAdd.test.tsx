import React from 'react'
import { render, screen } from '@testing-library/react'
import FuncionarioAdd from "./FuncionarioAdd";

test('renders learn react link', () => {
  render(<FuncionarioAdd />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
