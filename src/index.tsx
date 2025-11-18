import {useEffect, useRef} from 'react';
import DisplayControl from './NativeDisplayControl';

export function activate() {
  return DisplayControl.activate();
}

export function deactivate() {
  return DisplayControl.deactivate();
}

export function setBrightness(brightness: number) {
  if (brightness > 1) {
    console.error('max brightness is 1, ', 'your brightness is: ', brightness);
  }
  return DisplayControl.setBrightness(brightness);
}

export function resetBrightness() {
  return DisplayControl.resetBrightness();
}

export function useMaxBrightness() {
  useEffect(() => {
    setBrightness(1);
    return () => {
      resetBrightness();
    };
  }, []);
}

export function useKeepAwake() {
  useEffect(() => {
    activate();
    return () => {
      deactivate();
    };
  }, []);
}
