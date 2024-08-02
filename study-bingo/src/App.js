import './App.css';
import Play from './component/Play'

// 화살표 함수 (선언형과 표현식의 차이)
// 함수 선언식 : function App() { return (); }
// 함수 표현식 : const App = function(){ return (); };
// 화살표 함수 : const App = () => { };
const App = () => (
    <Play />
);

export default App;
