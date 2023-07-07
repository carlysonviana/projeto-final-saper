import React from 'react'
import { render, screen } from '@testing-library/react'
import FuncionarioEdit from "./FuncionarioEdit";

test('renders learn react link', () => {
  render(<FuncionarioEdit />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
