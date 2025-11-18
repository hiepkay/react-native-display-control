import {TurboModuleRegistry, type TurboModule} from 'react-native';

export interface Spec extends TurboModule {
  activate(): void;
  deactivate(): void;
  setBrightness(value: number): void;
  resetBrightness(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('DisplayControl');
