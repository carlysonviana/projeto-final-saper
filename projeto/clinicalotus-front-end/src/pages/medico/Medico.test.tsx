import React from 'react'
import { render, screen } from '@testing-library/react'
import Medico from "./Medico";

test('renders learn react link', () => {
  render(<Medico/>)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
