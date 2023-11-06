import { render, screen } from '@testing-library/react';
import MainMenu from './Screens/MainMenu/MainMenu';

test('renders learn react link', () => {
  render(<MainMenu />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
