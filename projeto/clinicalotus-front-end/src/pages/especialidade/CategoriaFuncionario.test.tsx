import React from 'react'
import { render, screen } from '@testing-library/react'
import Especialidade from "./Especialidade";


test('renders learn react link', () => {
  // eslint-disable-next-line react/jsx-no-undef
  render(<Especialidade />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
