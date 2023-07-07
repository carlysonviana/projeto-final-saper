import React from 'react'
import { render, screen } from '@testing-library/react'
import ProfileEdit from "./ProfileEdit";

test('renders learn react link', () => {
  render(<ProfileEdit />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
